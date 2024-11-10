package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.OrderDetail;
import com.koi151.QTDL.entity.Agency;
import com.koi151.QTDL.entity.Order;
import com.koi151.QTDL.entity.Employee;
import com.koi151.QTDL.entity.keys.OrderDetailKey;
import com.koi151.QTDL.enums.OrderStatusEnum;
import com.koi151.QTDL.model.dto.OrderDetailsDTO;
import com.koi151.QTDL.model.request.create.OrderCreateRequest;
import com.koi151.QTDL.model.request.update.OrderUpdateRequest;
import com.koi151.QTDL.repository.AgencyRepository;
import com.koi151.QTDL.repository.OrderRepository;
import com.koi151.QTDL.repository.EmployeeRepository;
import com.koi151.QTDL.repository.ProductRepository;
import com.koi151.QTDL.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AgencyRepository agencyRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    @Override
    public Page<OrderDetailsDTO> findOrders(Pageable pageable) {
        return orderRepository.findAllByDeleted(false, pageable)
            .map(order -> OrderDetailsDTO.builder()
                .orderId(order.getOrderId())
                .agencyName(order.getAgency().getAgencyName())
                .status(order.getStatus().name())
                .notes(order.getNotes())
                .phone(order.getEmployee().getPhone())
                .employeeName(order.getEmployee().getFullName())
                .build());
    }

    @Override
    @Transactional
    public OrderDetailsDTO createOrder(OrderCreateRequest request) {
        Agency agency = agencyRepository.findById(request.getAgencyId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với mã: " + request.getAgencyId()));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với mã: " + request.getEmployeeId()));

        Order order = Order.builder()
            .agency(agency)
            .employee(employee)
            .notes(request.getNotes())
            .orderDetails(new ArrayList<>())
            .status(OrderStatusEnum.processing)
            .build();

        request.getOrderDetails().forEach(detailedRequest -> {
            var product = productRepository.findById(detailedRequest.getProductId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy sản phẩm với mã: " + detailedRequest.getProductId()));

            var ctDatHangKey = new OrderDetailKey(order.getOrderId(), product.getProductId());
            var ctDatHang = OrderDetail.builder()
                .orderDetailKey(ctDatHangKey)
                .order(order)
                .product(product)
                .quantity(detailedRequest.getQuantity())
                .build();

            order.getOrderDetails().add(ctDatHang);
        });

        Order savedOrderEntity = orderRepository.save(order);
        return OrderDetailsDTO.builder()
            .orderId(savedOrderEntity.getOrderId())
            .agencyName(savedOrderEntity.getAgency().getAgencyName())
            .employeeName(savedOrderEntity.getEmployee().getFullName())
            .phone(savedOrderEntity.getEmployee().getPhone())
            .notes(savedOrderEntity.getNotes())
            .status(savedOrderEntity.getStatus().name())
            .build();
    }

    @Override
    @Transactional
    public OrderDetailsDTO patchUpdateOrder(Long orderId, OrderUpdateRequest request) {
        // Tìm Order hiện có hoặc trả về lỗi nếu không tồn tại
        Order existingOrder = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đơn đặt hàng với mã: " + orderId));

        // Chỉ cập nhật các trường có trong yêu cầu
        if (request.getAgencyId() != null) {
            Agency agency = agencyRepository.findById(request.getAgencyId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với mã: " + request.getAgencyId()));
            existingOrder.setAgency(agency);
        }

        if (request.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với mã: " + request.getEmployeeId()));
            existingOrder.setEmployee(employee);
        }

        if (request.getNotes() != null) {
            existingOrder.setNotes(request.getNotes());
        }

        if (request.getStatus() != null) {
            existingOrder.setStatus(request.getStatus());
        }

        // Cập nhật các chi tiết đơn hàng nếu có
        if (request.getOrderDetails() != null) {
            request.getOrderDetails().forEach(detailedRequest -> {
                var product = productRepository.findById(detailedRequest.getProductId())
                    .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy sản phẩm với mã: " + detailedRequest.getProductId()));

                var orderDetailKey = new OrderDetailKey(orderId, product.getProductId());

                // Tìm OrderDetail nếu đã tồn tại, nếu không thì tạo mới
                OrderDetail orderDetail = existingOrder.getOrderDetails().stream()
                    .filter(detail -> detail.getOrderDetailKey().equals(orderDetailKey))
                    .findFirst()
                    .orElse(OrderDetail.builder()
                        .orderDetailKey(orderDetailKey)
                        .order(existingOrder)
                        .product(product)
                        .build());

                // Chỉ cập nhật số lượng nếu có trong yêu cầu
                if (detailedRequest.getQuantity() != null) {
                    orderDetail.setQuantity(detailedRequest.getQuantity());
                }

                // Thêm hoặc thay thế chi tiết trong danh sách chi tiết của Order
                if (!existingOrder.getOrderDetails().contains(orderDetail)) {
                    existingOrder.getOrderDetails().add(orderDetail);
                }
            });
        }

        // Lưu Order sau khi cập nhật
        Order savedOrderEntity = orderRepository.save(existingOrder);

        // Trả về DTO của Order đã cập nhật
        return OrderDetailsDTO.builder()
            .orderId(savedOrderEntity.getOrderId())
            .agencyName(savedOrderEntity.getAgency().getAgencyName())
            .employeeName(savedOrderEntity.getEmployee().getFullName())
            .phone(savedOrderEntity.getEmployee().getPhone())
            .notes(savedOrderEntity.getNotes())
            .status(savedOrderEntity.getStatus().name())
            .build();
    }


    @Override
    public void deleteOrder(Long id) {
        Order dh = orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại đơn đặt hàng với id: " + id));

        dh.setDeleted(true);
        orderRepository.save(dh);
    }
}
