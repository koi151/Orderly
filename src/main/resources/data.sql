-- INSERT INTO role (role_name, created_at, updated_at, deleted)
-- VALUES ('Nhân viên', NOW(), NOW(), FALSE);

INSERT INTO role (role_id, role_name, description, deleted, created_at, updated_at) VALUES
    (1, 'Quản trị viên', 'Vai trò quản lý hệ thống', FALSE, NOW(), NOW()),
    (2, 'Quản lý', 'Vai trò quản lý nhóm', FALSE, NOW(), NOW()),
    (3, 'Nhân viên', 'Vai trò nhân viên làm việc', FALSE, NOW(), NOW());

INSERT INTO supplier (supplier_id, supplier_name, address, rep_info, deleted, created_at, updated_at) VALUES
      (1, 'Văn phòng phẩm A', '123 Đường A, Hà Nội', 'Nguyễn Văn A', FALSE, NOW(), NOW()),
      (2, 'Văn phòng phẩm B', '456 Đường B, TP.HCM', 'Trần Thị B', FALSE, NOW(), NOW()),
      (3, 'Văn phòng phẩm C', '789 Đường C, Đà Nẵng', 'Lê Văn C', FALSE, NOW(), NOW()),
      (4, 'Văn phòng phẩm D', '101 Đường D, Hải Phòng', 'Phạm Thị D', FALSE, NOW(), NOW()),
      (5, 'Văn phòng phẩm E', '202 Đường E, Cần Thơ', 'Vũ Văn E', FALSE, NOW(), NOW());

INSERT INTO product_category (category_id, category_name, description, deleted, created_at, updated_at) VALUES
    (1, 'Giấy văn phòng', 'Các loại giấy cho văn phòng', FALSE, NOW(), NOW()),
    (2, 'Bút viết', 'Các loại bút viết', FALSE, NOW(), NOW()),
    (3, 'Băng keo và bấm dán', 'Các loại băng keo và bấm dán', FALSE, NOW(), NOW()),
    (4, 'Bảng trắng và phấn', 'Các loại bảng trắng và phấn', FALSE, NOW(), NOW()),
    (5, 'Tổ chức và lưu trữ', 'Các sản phẩm tổ chức và lưu trữ', FALSE, NOW(), NOW()),
    (6, 'Máy tính và phụ kiện', 'Máy tính và các phụ kiện liên quan', FALSE, NOW(), NOW()),
    (7, 'Đồ dùng văn phòng khác', 'Các đồ dùng văn phòng khác', FALSE, NOW(), NOW()),
    (8, 'Vải băng và keo dán', 'Các loại vải băng và keo dán', FALSE, NOW(), NOW()),
    (9, 'Bản vẽ và đồ họa', 'Các dụng cụ bản vẽ và đồ họa', FALSE, NOW(), NOW()),
    (10, 'Thiết bị in ấn', 'Các thiết bị in ấn', FALSE, NOW(), NOW());

INSERT INTO agency (agency_id, agency_name, phone, address, rep_info, deleted, created_at, updated_at) VALUES
   (1, 'Đại lý Hà Nội', '0123456789', '10 Đường X, Hà Nội', 'Nguyễn Văn X', FALSE, NOW(), NOW()),
   (2, 'Đại lý TP.HCM', '0987654321', '20 Đường Y, TP.HCM', 'Trần Thị Y', FALSE, NOW(), NOW()),
   (3, 'Đại lý Đà Nẵng', '0112233445', '30 Đường Z, Đà Nẵng', 'Lê Văn Z', FALSE, NOW(), NOW()),
   (4, 'Đại lý Hải Phòng', '0223344556', '40 Đường A1, Hải Phòng', 'Phạm Thị A1', FALSE, NOW(), NOW()),
   (5, 'Đại lý Cần Thơ', '0334455667', '50 Đường B1, Cần Thơ', 'Vũ Văn B1', FALSE, NOW(), NOW()),
   (6, 'Đại lý Nha Trang', '0445566778', '60 Đường C1, Nha Trang', 'Đặng Văn C1', FALSE, NOW(), NOW());

INSERT INTO product (product_id, product_name, price, category_id, supplier_id, deleted, created_at, updated_at) VALUES
     (1, 'Giấy in A4 80gsm', 50.00, 1, 1, FALSE, NOW(), NOW()),
     (2, 'Giấy ghi chú', 30.00, 1, 1, FALSE, NOW(), NOW()),
     (3, 'Bút bi X1', 15.00, 2, 2, FALSE, NOW(), NOW()),
     (4, 'Bút chì HB', 10.00, 2, 2, FALSE, NOW(), NOW()),
     (5, 'Băng keo Scotch', 20.00, 3, 3, FALSE, NOW(), NOW()),
     (6, 'Băng keo dán vải', 25.00, 3, 3, FALSE, NOW(), NOW()),
     (7, 'Bảng trắng mini', 100.00, 4, 4, FALSE, NOW(), NOW()),
     (8, 'Phấn viết bảng trắng', 5.00, 4, 4, FALSE, NOW(), NOW()),
     (9, 'Hộp đựng tài liệu', 200.00, 5, 5, FALSE, NOW(), NOW()),
     (10, 'Thùng đựng bút', 80.00, 5, 5, FALSE, NOW(), NOW()),
     (11, 'Chuột máy tính', 150.00, 6, 1, FALSE, NOW(), NOW()),
     (12, 'Bàn phím máy tính', 200.00, 6, 1, FALSE, NOW(), NOW()),
     (13, 'Thư mục giấy', 40.00, 7, 2, FALSE, NOW(), NOW()),
     (14, 'Dụng cụ tổ chức tệp', 60.00, 7, 2, FALSE, NOW(), NOW()),
     (15, 'Keo dán lấp lỗ', 35.00, 8, 3, FALSE, NOW(), NOW()),
     (16, 'Vải băng dính', 45.00, 8, 3, FALSE, NOW(), NOW()),
     (17, 'Bút vẽ kỹ thuật', 50.00, 9, 4, FALSE, NOW(), NOW()),
     (18, 'Tập vẽ kỹ thuật', 120.00, 9, 4, FALSE, NOW(), NOW()),
     (19, 'Máy in phun', 3000.00, 10, 5, FALSE, NOW(), NOW()),
     (20, 'Máy photocopy mini', 2000.00, 10, 5, FALSE, NOW(), NOW()),
     (21, 'Giấy in màu A3', 70.00, 1, 1, FALSE, NOW(), NOW()),
     (22, 'Giấy photo', 90.00, 1, 1, FALSE, NOW(), NOW()),
     (23, 'Bút sắt XYZ', 25.00, 2, 2, FALSE, NOW(), NOW()),
     (24, 'Bút dạ không lem', 30.00, 2, 2, FALSE, NOW(), NOW()),
     (25, 'Băng keo chống thấm', 35.00, 3, 3, FALSE, NOW(), NOW()),
     (26, 'Băng keo UV', 40.00, 3, 3, FALSE, NOW(), NOW()),
     (27, 'Bảng trắng lớn', 200.00, 4, 4, FALSE, NOW(), NOW()),
     (28, 'Phấn viết cứng', 6.00, 4, 4, FALSE, NOW(), NOW()),
     (29, 'Tủ hồ sơ', 500.00, 5, 5, FALSE, NOW(), NOW()),
     (30, 'Hộp đựng bút và giấy', 90.00, 5, 5, FALSE, NOW(), NOW());