CREATE DATABASE QLNS;
GO

USE QLNS;
GO

SET DATEFORMAT DMY;
GO

--USE master
--GO
--DROP DATABASE QLNS
--GO
--Tao Bang -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE NHANVIEN
(
    MaNhanVien  CHAR(10) NOT NULL PRIMARY KEY,
    TenNhanVien NVARCHAR(50) NOT NULL,
	GioiTinh NCHAR(10) CHECK(GioiTinh IN (N'Nam',N'Nữ')) NOT NULL,
	NgaySinh DATE CHECK(NgaySinh < GETDATE()),
	SoCMND CHAR(13) NOT NULL UNIQUE,
	DienThoai CHAR(10) NOT NULL,
	TinhTrang BIT DEFAULT 1, --1: Đang làm việc, 0: Thôi việc
);

CREATE TABLE TAIKHOAN
(
    TenDN   CHAR(20) PRIMARY KEY,
    MatKhau CHAR(100) NOT NULL DEFAULT 1,
	MaNhanVien CHAR(10)  NOT NULL UNIQUE,

	CONSTRAINT FK_NV_TK FOREIGN KEY(MaNhanVien) REFERENCES dbo.NHANVIEN(MaNhanVien)
);

CREATE TABLE NHACUNGCAP
(
    MaNCC       CHAR(10) NOT NULL PRIMARY KEY,
    TenNCC      NVARCHAR(50) NOT NULL,
    DienThoai   CHAR(10) NOT NULL,
    TongTien	MONEY DEFAULT 0,
	TinhTrang BIT DEFAULT 1,--1: Đang cung cấp, ngừng cung cấp
);

CREATE TABLE NHOMSACH
(
    MaNhomSach CHAR(10) NOT NULL PRIMARY KEY,
    TenNhom    NVARCHAR(40) NOT NULL,
	TinhTrang BIT DEFAULT 1, --1. Đang bán, ngừng bán
);

CREATE TABLE SACH
(
    MaSach     CHAR(10)     NOT NULL PRIMARY KEY,
    TenSach    NVARCHAR(50) NOT NULL,
    TacGia     NVARCHAR(50),
	GiaNhap MONEY NOT NULL DEFAULT 0,
	GiaBan MONEY NOT NULL DEFAULT 0,
    SoLuong    INT          CHECK ( SoLuong >= 0 ) DEFAULT 0,
    MaNhomSach CHAR(10),
    TinhTrang  BIT          DEFAULT 1, --1. Đang bán, ngừng bán

    CONSTRAINT FK_S_NS FOREIGN KEY ( MaNhomSach ) REFERENCES dbo.NHOMSACH(MaNhomSach)
);

CREATE TABLE NHOMKHACHHANG
(
    MaNhomKhachHang CHAR(10) NOT NULL PRIMARY KEY,
    TenNhom         NVARCHAR(50) NOT NULL,
    GhiChu          NVARCHAR(60),
);

CREATE TABLE KHACHHANG
(
    MaKhachHang     CHAR(10)    NOT NULL PRIMARY KEY,
    TenKhachHang    NVARCHAR(40) NOT NULL,
    MaNhomKhachHang CHAR(10) NOT NULL,
    DienThoai       CHAR(10) NOT NULL UNIQUE,
    TongTien		MONEY DEFAULT 0,
	TinhTrang BIT DEFAULT 1 --1. Binh thuong, Chan khach hang
	
	CONSTRAINT FK_KH_NKH FOREIGN KEY ( MaNhomKhachHang ) REFERENCES NHOMKHACHHANG ( MaNhomKhachHang ),
);

--Hoa don cho phep xoa, nhung khi do se xoa trong ca chi tiet hoa don va cap nhap lai so luong hang


CREATE TABLE HOADON
(
    MaHoaDon    CHAR(10) NOT NULL PRIMARY KEY,
    MaNhanVien  CHAR(10),
    MaKhachHang CHAR(10),
    NgayBan     DATE DEFAULT GETDATE() CHECK(NgayBan <= GETDATE()),
    TongTien	MONEY CHECK( TongTien >= 0),

    CONSTRAINT FK_HD_NV FOREIGN KEY ( MaNhanVien ) REFERENCES NHANVIEN ( MaNhanVien ),
    CONSTRAINT FK_HD_KH FOREIGN KEY ( MaKhachHang ) REFERENCES KHACHHANG ( MaKhachHang ),
);

CREATE TABLE CHITIETHOADON
(
    MaHoaDon  CHAR(10) NOT NULL,
    MaSach    CHAR(10) NOT NULL,
	DonGia MONEY NOT NULL DEFAULT 0,
    SoLuong   INT CHECK(SoLuong >= 0),

    CONSTRAINT PK_CTHD PRIMARY KEY (MaHoaDon,MaSach ),
	CONSTRAINT FK_HD_CTHD FOREIGN KEY(MaHoaDon) REFERENCES dbo.HOADON(MaHoaDon),
	CONSTRAINT FK_CTHD_S FOREIGN KEY(MaSach) REFERENCES dbo.SACH(MaSach),
);

--Hoa don cho phep xoa, nhung khi do se xoa trong ca chi tiet nhap hang va cap nhap lai so luong hang
CREATE TABLE PHIEUNHAP
(
    MaPhieuNhap CHAR(10) NOT NULL PRIMARY KEY,
    MaNhanVien  CHAR(10),
    NgayNhap    DATE,
    MaNCC       CHAR(10),
    TongTien    MONEY CHECK(TongTien >= 0),

	CONSTRAINT FK_PN_NV FOREIGN KEY ( MaNhanVien ) REFERENCES NHANVIEN ( MaNhanVien ),
    CONSTRAINT FK_PN_NCC FOREIGN KEY ( MaNCC ) REFERENCES NHACUNGCAP ( MaNCC ),
);


CREATE TABLE CHITIETPHIEUNHAP
(
    MaPhieuNhap CHAR(10) NOT NULL,
    MaSach      CHAR(10) NOT NULL,
	DonGia MONEY NOT NULL DEFAULT 0,
    SoLuong     INT CHECK(SoLuong >= 0),

    CONSTRAINT PK_CTPN PRIMARY KEY (MaPhieuNhap, MaSach ),
	CONSTRAINT FK_PN_S FOREIGN KEY(MaSach) REFERENCES dbo.SACH(MaSach),
	CONSTRAINT FK_PN_CTPN FOREIGN KEY(MaPhieuNhap) REFERENCES dbo.PHIEUNHAP(MaPhieuNhap),
);



--Nhap Lieu------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO NHANVIEN(MaNhanVien,TenNhanVien,GioiTinh,NgaySinh,SoCMND,DienThoai)
VALUES
    ( 'NV001', N'Phan Hồ Thái Anh',N'Nam','18/09/2000','215678987','098765890'),
    ( 'NV002', N'Nguyễn Lưu Nguyệt',N'Nữ','18/12/2001','289786789','098880987' ),
    ( 'NV003', N'Phạm Ánh Nguyệt',N'Nữ','19/02/2002','223322333','0986578321' );

INSERT INTO TAIKHOAN(TenDN,MatKhau,MaNhanVien)
VALUES
    ( 'admin', '523af537946b79c4f8369ed39ba78605', 'NV001'),
	( '002', 'c4ca4238a0b923820dcc509a6f75849b', 'NV002'),
	( '003', 'c4ca4238a0b923820dcc509a6f75849b', 'NV003');


INSERT INTO NHOMKHACHHANG(MaNhomKhachHang,TenNhom,GhiChu)
VALUES
    ( 'NKH001', N'Vãng Lai', NULL ),
    ( 'NKH002', N'VIP', NULL ),
    ( 'NKH003', N'Tiềm Năng', NULL );

INSERT INTO KHACHHANG(MaKhachHang,TenKhachHang,MaNhomKhachHang,DienThoai,TongTien)
VALUES
    ( 'KH001', N'Nguyễn Thái Sơn', 'NKH001', '0356842568', NULL ),
    ( 'KH002', N'Phạm Thành Lộc', 'NKH001', '0842563958', NULL ),
    ( 'KH003', N'Hồ Quý Phi', 'NKH002', '0341576254', NULL ),
    ( 'KH004', N'Lưu Văn Anh', 'NKH001', '0345263587', NULL ),
    ( 'KH005', N'Phạm Hồ Quỳnh Anh', 'NKH003', '024586145', NULL );


INSERT INTO NHOMSACH(MaNhomSach,TenNhom)
VALUES
    ( 'NS001', N'Cổ Tích' ),
    ( 'NS002', N'Văn Học' ),
    ( 'NS003', N'Khoa Học' ),
    ( 'NS004', N'Lịch Sử' );


INSERT INTO SACH ( MaSach, TenSach, TacGia,GiaNhap,GiaBan, SoLuong, MaNhomSach )
VALUES
    ( 'S001', 'The Six Swans', 'Wilhelm Carl Grimm',50000,20000, 0, 'NS001' ),
    ( 'S002', 'The French Revolution', 'William Doyle',50000,20000, 0, 'NS004' ),
    ( 'S003', 'The Twelve Dancing Princesses', 'Jacob Ludwig Carl Grimm',50000,20000, 0, 'NS001' ),
    ( 'S004', 'Villette', 'Charlotte Bronte',50000,20000, 0, 'NS002' ),
    ( 'S005', 'The Immortal Life of Henrietta Lacks', 'Rebecca Skloot',50000,20000, 0, 'NS003' ),
    ( 'S006', 'Marie Antoinette: The Journey', 'Antonia Fraser',50000,20000, 0, 'NS002' );


INSERT INTO NHACUNGCAP(MaNCC,TenNCC,DienThoai,TongTien)
VALUES
    ( 'NCC001', N'Nhà Xuất Bản Kim Đồng', '0345864712', 0 ),
    ( 'NCC002', N'Nhà Xuất Bản Thiếu Nhi', '0247568211', 0 ),
    ( 'NCC003', N'Nhà Xuất Bản Thanh Niên', '0151481586', 0 );

INSERT INTO PHIEUNHAP(MaPhieuNhap,MaNhanVien,NgayNhap,MaNCC,TongTien)
VALUES
    ( 'PN001', 'NV003', '21/02/2020', 'NCC002', 0 ),
    ( 'PN002', 'NV001', '23/02/2020', 'NCC003', 0 ),
    ( 'PN003', 'NV002', '25/02/2020', 'NCC001', 0 );

INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong)
VALUES
    ( 'PN001', 'S006',50000,0 ),
    ( 'PN001', 'S001',50000,0 ),
    ( 'PN002', 'S004',50000,0 ),
    ( 'PN002', 'S002',50000,0 ),
    ( 'PN003', 'S003',50000,0 ),
    ( 'PN003', 'S005',50000,0 );

INSERT INTO HOADON(MaHoaDon,MaNhanVien,MaKhachHang,NgayBan,TongTien)
VALUES
    ( 'HD001', 'NV001', 'KH003', '21/02/2021', 0 ),
    ( 'HD002', 'NV003', 'KH001', '23/02/2021', 0 ),
    ( 'HD003', 'NV002', 'KH005', '25/02/2021', 0 );


INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong)
VALUES
    ( 'HD001', 'S006',20000, 0),
    ( 'HD001', 'S001',20000, 0),
    ( 'HD002', 'S002',20000, 0),
    ( 'HD002', 'S005',20000, 0),
    ( 'HD003', 'S003',20000, 0),
    ( 'HD003', 'S004',20000, 0);

GO

CREATE PROC P_DeleteAllData
AS
BEGIN
    DELETE dbo.CHITIETHOADON
	DELETE dbo.CHITIETPHIEUNHAP
	DELETE dbo.PHIEUNHAP
	DELETE dbo.HOADON
	DELETE dbo.SACH
	DELETE dbo.NHOMSACH
	DELETE dbo.TAIKHOAN
	DELETE dbo.NHANVIEN
	DELETE dbo.KHACHHANG
	DELETE dbo.NHACUNGCAP
	DELETE dbo.NHOMKHACHHANG
END;
GO

--EXEC dbo.P_DeleteAllData