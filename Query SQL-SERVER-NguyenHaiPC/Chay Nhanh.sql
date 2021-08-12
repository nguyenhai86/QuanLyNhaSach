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
    TenNCC      NVARCHAR(50) NOT NULL UNIQUE,
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
    ( 'KH001', N'Khách vô danh', 'NKH001', '0', NULL ),
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
    ( 'S001', 'The Six Swans', 'Wilhelm Carl Grimm',70000,80000, 0, 'NS001' ),
    ( 'S002', 'The French Revolution', 'William Doyle',60000,90000, 0, 'NS004' ),
    ( 'S003', 'The Twelve Dancing Princesses', 'Jacob Ludwig Carl Grimm',100000,120000, 0, 'NS001' ),
    ( 'S004', 'Villette', 'Charlotte Bronte',50000,20000, 0, 'NS002' ),
    ( 'S005', 'The Immortal Life of Henrietta Lacks', 'Rebecca Skloot',50000,20000, 0, 'NS003' ),
    ( 'S006', 'Marie Antoinette: The Journey', 'Antonia Fraser',50000,20000, 0, 'NS002' );


INSERT INTO NHACUNGCAP(MaNCC,TenNCC,DienThoai)
VALUES
    ( 'NCC001', N'Nhà Xuất Bản Kim Đồng', '0345864712'),
    ( 'NCC002', N'Nhà Xuất Bản Thiếu Nhi', '0247568211'),
    ( 'NCC003', N'Nhà Xuất Bản Thanh Niên', '0151481586');

INSERT INTO PHIEUNHAP(MaPhieuNhap,MaNhanVien,NgayNhap,MaNCC)
VALUES
    ( 'PN001', 'NV003', '21/02/2020', 'NCC002'),
    ( 'PN002', 'NV001', '23/02/2020', 'NCC003'),
    ( 'PN003', 'NV002', '25/02/2020', 'NCC001');
GO

INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong) VALUES    ( 'PN001', 'S006',50000,2 )
INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong) VALUES    ( 'PN001', 'S001',70000,3 )
INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong) VALUES    ( 'PN002', 'S004',50000,4 )
INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong) VALUES    ( 'PN002', 'S002',60000,5 )
INSERT INTO CHITIETPHIEUNHAP(MaPhieuNhap,MaSach,DonGia,SoLuong) VALUES    ( 'PN003', 'S005',100000,2 )


INSERT INTO HOADON(MaHoaDon,MaNhanVien,MaKhachHang,NgayBan)
VALUES
    ( 'HD001', 'NV001', 'KH003', '21/02/2021' ),
    ( 'HD002', 'NV003', 'KH001', '23/02/2021' ),
    ( 'HD003', 'NV002', 'KH005', '25/02/2021' );
GO

INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES    ( 'HD001', 'S006',20000, 1)
INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES    ( 'HD001', 'S001',80000, 2)
INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES    ( 'HD002', 'S002',90000, 2)
INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES    ( 'HD002', 'S005',20000, 1)
INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES    ( 'HD003', 'S003',120000, 2)
INSERT INTO CHITIETHOADON(MaHoaDon,MaSach,DonGia, SoLuong) VALUES	 ( 'HD003', 'S004',20000, 3)

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

--=====================================================================================================================================================
CREATE TRIGGER TRG_name_triger
ON dbo.NHOMSACH
FOR UPDATE
AS
	IF( EXISTS(SELECT Inserted.TinhTrang FROM Inserted WHERE Inserted.TinhTrang = 1 ) AND EXISTS(SELECT Deleted.TinhTrang FROM Deleted WHERE Deleted.TinhTrang = 0 ))
	BEGIN
		UPDATE dbo.SACH SET TinhTrang = 1
		WHERE MaSach IN (	SELECT MaSach FROM dbo.SACH
							JOIN Inserted ON Inserted.MaNhomSach = SACH.MaNhomSach )
	END
	ELSE IF( EXISTS(SELECT Inserted.TinhTrang FROM Inserted WHERE Inserted.TinhTrang = 0 ) AND EXISTS(SELECT Deleted.TinhTrang FROM Deleted WHERE Deleted.TinhTrang = 1 ))
	BEGIN
		UPDATE dbo.SACH SET TinhTrang = 0
		WHERE MaSach IN (	SELECT MaSach FROM dbo.SACH
							JOIN Inserted ON Inserted.MaNhomSach = SACH.MaNhomSach )
	END
GO

--UPDATE dbo.NHOMSACH SET TinhTrang = 1 WHERE MaNhomSach = 'NS001'

--SELECT * FROM dbo.SACH
--JOIN dbo.NHOMSACH ON NHOMSACH.MaNhomSach = SACH.MaNhomSach
--WHERE NHOMSACH.MaNhomSach = 'NS001'

--===================================================================================================================================================================================================

UPDATE dbo.SACH SET SoLuong = 2 WHERE MaSach = 'S003'
GO

CREATE TRIGGER TRG_ThemChiTietHoaDon
ON dbo.CHITIETHOADON
FOR INSERT
AS
	UPDATE dbo.SACH SET SoLuong = SoLuong - (SELECT Inserted.SoLuong FROM Inserted)
	WHERE SACH.MaSach IN (SELECT Inserted.MaSach FROM Inserted)
	UPDATE dbo.HOADON SET TongTien = (SELECT SUM(DonGia*SoLuong) FROM dbo.CHITIETHOADON WHERE MaHoaDon = (SELECT MaHoaDon FROM Inserted))
	WHERE HOADON.MaHoaDon = (SELECT MaHoaDon FROM Inserted)
GO


SELECT * FROM dbo.HOADON
JOIN dbo.CHITIETHOADON ON CHITIETHOADON.MaHoaDon = HOADON.MaHoaDon
GO

--===================================================================================================================================================================================================
CREATE TRIGGER TRG_ThemChiTietPhieuNhap
ON dbo.CHITIETPHIEUNHAP
FOR INSERT
AS
	UPDATE dbo.SACH SET SoLuong = SoLuong + (SELECT Inserted.SoLuong FROM Inserted)
	WHERE SACH.MaSach IN (SELECT Inserted.MaSach FROM Inserted)

	UPDATE dbo.PhieuNhap SET TongTien = (SELECT SUM(DonGia*SoLuong) FROM dbo.CHITIETPHIEUNHAP WHERE MaPhieuNhap = (SELECT Inserted.MaPhieuNhap FROM Inserted))
	WHERE PHIEUNHAP.MaPhieuNhap = (SELECT Inserted.MaPhieuNhap FROM Inserted)

	UPDATE dbo.NHACUNGCAP SET TongTien = ( SELECT SUM(TongTien) FROM dbo.PHIEUNHAP WHERE MaNCC = ( SELECT dbo.PHIEUNHAP.MaNCC FROM Inserted JOIN dbo.PHIEUNHAP ON PHIEUNHAP.MaPhieuNhap = Inserted.MaPhieuNhap ) )
	WHERE NHACUNGCAP.MaNCC = ( SELECT dbo.PHIEUNHAP.MaNCC FROM Inserted JOIN dbo.PHIEUNHAP ON PHIEUNHAP.MaPhieuNhap = Inserted.MaPhieuNhap )
GO



--=========================================================================================================================================================================

--================================================================================================================================================================================================
CREATE PROC P_GetAllKhachHang
AS
BEGIN
    SELECT dbo.KHACHHANG.MaKhachHang, TenKhachHang, TenNhom, DienThoai, TruyVanCon.TongTien , CONVERT(CHAR(10),TruyVanCon.LanCuoiMuaHang,103) AS LanCuoiMuaHang, TinhTrang
    FROM dbo.KHACHHANG
    JOIN dbo.NHOMKHACHHANG ON NHOMKHACHHANG.MaNhomKhachHang = KHACHHANG.MaNhomKhachHang
    LEFT JOIN
           (   SELECT MaKhachHang, MAX(NgayBan) AS 'LanCuoiMuaHang', SUM(TongTien) AS 'TongTien'
               FROM dbo.HOADON
               GROUP BY MaKhachHang ) TruyVanCon ON TruyVanCon.MaKhachHang = KHACHHANG.MaKhachHang
	ORDER BY CONVERT(INT,SUBSTRING(KHACHHANG.MaKhachHang,3,100)) DESC
END;
GO


CREATE PROC P_SeachKH
	@MaKH CHAR(10), @TenKH NVARCHAR(40), @DienThoai CHAR(10)
AS
BEGIN
    SELECT dbo.KHACHHANG.MaKhachHang, TenKhachHang, TenNhom, DienThoai, TruyVanCon.TongTien , CONVERT(CHAR(10),TruyVanCon.LanCuoiMuaHang,103) AS LanCuoiMuaHang, TinhTrang
    FROM dbo.KHACHHANG
     JOIN dbo.NHOMKHACHHANG ON NHOMKHACHHANG.MaNhomKhachHang = KHACHHANG.MaNhomKhachHang
     LEFT JOIN
           (   SELECT MaKhachHang, MAX(NgayBan) AS 'LanCuoiMuaHang', SUM(TongTien) AS 'TongTien'
               FROM dbo.HOADON
               GROUP BY MaKhachHang ) TruyVanCon ON TruyVanCon.MaKhachHang = KHACHHANG.MaKhachHang
	WHERE KHACHHANG.MaKhachHang LIKE @MaKH OR TenKhachHang LIKE @TenKH OR DienThoai = @DienThoai 
	ORDER BY CONVERT(INT,SUBSTRING(KHACHHANG.MaKhachHang,3,100)) DESC
END;
GO


--================================================================================================================================================================================================

CREATE PROC P_GetAllNhomKhachHang
AS
BEGIN
    SELECT * FROM NHOMKHACHHANG
	ORDER BY CONVERT(INT,SUBSTRING(NHOMKHACHHANG.MaNhomKhachHang,4,100)) DESC
END;
GO



--============================================================================================================================================================================================

CREATE PROC P_getNhaCungCap
AS
BEGIN
    SELECT MaNCC, TenNCC, DienThoai, TinhTrang FROM NHACUNGCAP
	
END;
GO


--==================================================================================================================================================

CREATE PROC P_GetAllHoaDon
AS
BEGIN
	SELECT MaHoaDon,CONVERT(CHAR(12),NgayBan,103) AS NgayBan, TenNhanVien, TenKhachHang, hd.TongTien
	FROM HOADON  hd 
	JOIN NHANVIEN nv on nv.MaNhanVien= hd.MaNhanVien
	JOIN KHACHHANG kh on kh.MaKhachHang = hd.MaKhachHang
	ORDER BY CONVERT(INT,SUBSTRING(HD.MaHoaDon,3,100)) DESC
END;
GO



--==================================================================================================================================================

CREATE PROC P_GetAllSach
AS
BEGIN
	SELECT MaSach,TenSach,TacGia,GiaNhap,GiaBan,SoLuong,dbo.NHOMSACH.TenNhom,dbo.SACH.TinhTrang FROM dbo.SACH 
	JOIN dbo.NHOMSACH ON NHOMSACH.MaNhomSach = SACH.MaNhomSach
	ORDER BY CONVERT(INT,SUBSTRING(dbo.SACH.MaSach,2,100)) DESC
END

GO



--==================================================================================================================================================

CREATE PROC P_GetCTHDByMa @maHoaDon char(10)
AS
BEGIN
	SELECT SACH.MaSach, TenSach, CHITIETHOADON.SoLuong, DonGia FROM dbo.CHITIETHOADON
	JOIN dbo.SACH ON SACH.MaSach = CHITIETHOADON.MaSach
	WHERE MaHoaDon = @maHoaDon
	ORDER BY CONVERT(INT,SUBSTRING(dbo.SACH.MaSach,2,100)) ASC
END
GO


--==================================================================================================================================================
CREATE PROC P_getALLNhanVien
AS
BEGIN
	SELECT MaNhanVien,TenNhanVien,GioiTinh,CONVERT(CHAR(10),NgaySinh,103) AS NgaySinh,SoCMND,DienThoai,TinhTrang FROM  dbo.NHANVIEN
	ORDER BY CONVERT(INT,SUBSTRING(dbo.NHANVIEN.MaNhanVien,3,100)) DESC
END
GO



--==================================================================================================================================================
CREATE PROC P_GetAllTaiKhoan
AS
BEGIN
	SELECT TenDN,NHANVIEN.MaNhanVien, TenNhanVien FROM dbo.TAIKHOAN
	JOIN dbo.NHANVIEN ON NHANVIEN.MaNhanVien = TAIKHOAN.MaNhanVien
	ORDER BY CONVERT(INT,SUBSTRING(dbo.NHANVIEN.MaNhanVien,3,100)) DESC
END
GO
--==================================================================================================================================================
CREATE PROC P_GetAllPhieuNhap
AS
BEGIN
	SELECT pn.MaPhieuNhap, CONVERT(CHAR(10), PN.NgayNhap, 103) AS NgayNhap, NV.TenNhanVien, ncc.TenNCC FROM dbo.PHIEUNHAP PN
	JOIN dbo.NHACUNGCAP NCC ON NCC.MaNCC = PN.MaNCC
	JOIN dbo.NHANVIEN NV ON NV.MaNhanVien = PN.MaNhanVien
	ORDER BY CONVERT(INT,SUBSTRING(PN.MaPhieuNhap,3,100)) DESC
END;
GO


--==================================================================================================================================================

CREATE PROC P_GetCTPNByMa @maPhieuNhap char(10)
AS
BEGIN
	SELECT CTPN.MaSach, S.TenSach, CTPN.SoLuong, CTPN.DonGia  FROM dbo.CHITIETPHIEUNHAP CTPN
	JOIN dbo.SACH S ON S.MaSach = CTPN.MaSach
	WHERE CTPN.MaPhieuNhap = @maPhieuNhap
END
GO


--==================================================================================================================================================

CREATE PROC P_GetAllNhomSach
AS
BEGIN
	SELECT NHOMSACH.MaNhomSach, TenNhom, NHOMSACH.TinhTrang, SUM(SoLuong) AS SoLuong  FROM dbo.NHOMSACH
	LEFT JOIN dbo.SACH ON SACH.MaNhomSach = NHOMSACH.MaNhomSach
	GROUP BY NHOMSACH.MaNhomSach, TenNhom, NHOMSACH.TinhTrang
	ORDER BY CONVERT(INT,SUBSTRING(NHOMSACH.MaNhomSach,3,100)) DESC
END;
GO

--==================================================================================================================================================
CREATE PROC P_GetKhachHangBySDT
	@DienThoai CHAR(10)
AS
BEGIN
    SELECT dbo.KHACHHANG.MaKhachHang, TenKhachHang, TenNhom, DienThoai, TruyVanCon.TongTien , CONVERT(CHAR(10),TruyVanCon.LanCuoiMuaHang,103) AS LanCuoiMuaHang, TinhTrang
    FROM dbo.KHACHHANG
        JOIN dbo.NHOMKHACHHANG ON NHOMKHACHHANG.MaNhomKhachHang = KHACHHANG.MaNhomKhachHang
        LEFT JOIN
           (   SELECT MaKhachHang, MAX(NgayBan) AS 'LanCuoiMuaHang', SUM(TongTien) AS 'TongTien'
               FROM dbo.HOADON
               GROUP BY MaKhachHang ) TruyVanCon ON TruyVanCon.MaKhachHang = KHACHHANG.MaKhachHang
	WHERE DienThoai = @DienThoai
END;
GO

--==================================================================================================================================================
CREATE PROC P_GetKhachHangByMa
	@MaKH CHAR(10)
AS
BEGIN
    SELECT dbo.KHACHHANG.MaKhachHang, TenKhachHang, TenNhom, DienThoai, TruyVanCon.TongTien , CONVERT(CHAR(10),TruyVanCon.LanCuoiMuaHang,103) AS LanCuoiMuaHang, TinhTrang
    FROM dbo.KHACHHANG
        JOIN dbo.NHOMKHACHHANG ON NHOMKHACHHANG.MaNhomKhachHang = KHACHHANG.MaNhomKhachHang
        LEFT JOIN
           (   SELECT MaKhachHang, MAX(NgayBan) AS 'LanCuoiMuaHang', SUM(TongTien) AS 'TongTien'
               FROM dbo.HOADON
               GROUP BY MaKhachHang ) TruyVanCon ON TruyVanCon.MaKhachHang = KHACHHANG.MaKhachHang
	WHERE KHACHHANG.MaKhachHang = @MaKH
END;
GO

--==================================================================================================================================================

CREATE PROC P_GetNhomSachByTenNhom
	@TenNhom NVARCHAR(50)
AS
BEGIN
	SELECT NHOMSACH.MaNhomSach, TenNhom, NHOMSACH.TinhTrang, SUM(SoLuong) AS SoLuong  FROM dbo.NHOMSACH
	LEFT JOIN dbo.SACH ON SACH.MaNhomSach = NHOMSACH.MaNhomSach
	WHERE dbo.NHOMSACH.TenNhom = @TenNhom
	GROUP BY NHOMSACH.MaNhomSach, TenNhom, NHOMSACH.TinhTrang
END;
GO

--==================================================================================================================================================
CREATE PROC P_XuatHoaDon
	@MaHoaDon CHAR(10)
AS
BEGIN
	SELECT HOADON.MaHoaDon,TenKhachHang,TenNhanVien,CONVERT(CHAR(10),NgayBan,103) AS NgayBan,HOADON.TongTien,dbo.SACH.MaSach,TenSach,DonGia,CHITIETHOADON.SoLuong, DonGia*CHITIETHOADON.SoLuong  AS ThanhTien FROM dbo.HOADON
	JOIN dbo.KHACHHANG ON KHACHHANG.MaKhachHang = HOADON.MaKhachHang
	JOIN dbo.NHANVIEN ON NHANVIEN.MaNhanVien = HOADON.MaNhanVien
	JOIN dbo.CHITIETHOADON ON CHITIETHOADON.MaHoaDon = HOADON.MaHoaDon
	JOIN dbo.SACH ON SACH.MaSach = CHITIETHOADON.MaSach
	WHERE HOADON.MaHoaDon = @MaHoaDon
END
GO

--==================================================================================================================================================
CREATE FUNCTION F_MaPhieuNhapMoiNhat()
RETURNS CHAR(10)
AS
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.PHIEUNHAP
	DECLARE @MaPhieuNhap CHAR(10) = 'PN00' + CONVERT(CHAR(10),@dem)
	RETURN @MaPhieuNhap
END
GO


CREATE FUNCTION F_MaHoaDonMoiNhat()
RETURNS CHAR(10)
AS
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.HoaDon
	DECLARE @MaHoaDon CHAR(10) = 'HD00' + CONVERT(CHAR(10),@dem)
	RETURN @MaHoaDon
END
GO
--==================================================================================================================================================
CREATE FUNCTION F_TinhDoanhSo()
RETURNS MONEY
AS
BEGIN
	DECLARE @TongTien MONEY = 0
	SELECT @TongTien = SUM(TongTien) FROM dbo.HOADON
	RETURN @TongTien
END
GO


CREATE FUNCTION F_TinhLai()
RETURNS MONEY
AS
BEGIN
	DECLARE @DoanhThu MONEY = 0
	SELECT @DoanhThu = SUM(TongTien) FROM dbo.HOADON
	DECLARE @TongTien MONEY = 0
	SELECT @TongTien = SUM(TongTien) FROM dbo.PHIEUNHAP

	RETURN @DoanhThu - @TongTien
END
GO

CREATE FUNCTION F_SoDonHang()
RETURNS INT
AS
BEGIN
	DECLARE @Count INT = 0
	SELECT @Count = COUNT(*) FROM dbo.HOADON
	RETURN @Count
END
GO


CREATE FUNCTION F_TinhDoanhThuTheo(@NgayBatDau DATE, @NgayKetThuc DATE)
RETURNS MONEY
AS
BEGIN
	
	DECLARE @DoanhThu MONEY = 0
	SELECT @DoanhThu = SUM(TongTien) FROM dbo.HOADON WHERE NgayBan BETWEEN @NgayKetThuc AND @NgayBatDau


	RETURN @DoanhThu
END
GO



--==================================================================================================================================================
USE QLNS
GO

CREATE PROC P_UpdateKhachHang
	@MaKhachHang NCHAR(10),
	@TenKhachHang NVARCHAR(40),
	@DienThoai CHAR(10),
	@TenNhom NVARCHAR(50),
	@TinhTrang BIT
AS
BEGIN
	DECLARE @MaNhom CHAR(10)
	SELECT @MaNhom = MaNhomKhachHang FROM dbo.NHOMKHACHHANG WHERE TenNhom = @TenNhom

	UPDATE dbo.KHACHHANG SET TenKhachHang = @TenKhachHang, MaNhomKhachHang = @MaNhom, DienThoai = @DienThoai, TinhTrang = @TinhTrang
	WHERE MaKhachHang = @MaKhachHang
END
GO

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROC P_UpdateNhanVien
	@MaNhanVien CHAR(10),
	@TenNhanVien NVARCHAR(50),
	@GioiTinh NCHAR(10),
	@NgaySinh CHAR(10),
	@SoCMND CHAR(13),
	@DienThoai CHAR(10),
	@TinhTrang BIT
AS
BEGIN
	SET DATEFORMAT DMY
	UPDATE dbo.NHANVIEN SET TenNhanVien = @TenNhanVien, GioiTinh = @GioiTinh, NgaySinh = @NgaySinh, SoCMND = @SoCMND, DienThoai = @DienThoai, TinhTrang = @TinhTrang
	WHERE MaNhanVien = @MaNhanVien
END
GO
EXEC dbo.P_UpdateNhanVien @MaNhanVien = 'NV001', @TenNhanVien = N'Nguyễn Duy', @GioiTinh = N'Nam', @NgaySinh = '2021-07-27',
                          @SoCMND = '236699', @DienThoai = '09642263215', @TinhTrang = 1
GO
--------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROC P_UpdateNhomKhachHang
	@MaNhomKhachHang CHAR(10),
	@TenNhom NVARCHAR(50),
	@GhiChu NVARCHAR(60)
AS
BEGIN
	UPDATE dbo.NHOMKHACHHANG SET TenNhom = @TenNhom, GhiChu = @GhiChu
	WHERE MaNhomKhachHang = @MaNhomKhachHang
END
GO
-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC	P_UpdateSach
	@MaSach CHAR(10),
	@TenSach NVARCHAR(50),
	@TacGia NVARCHAR(50),
	@GiaNhap MONEY ,
	@GiaBan MONEY,
	@NhomSach NVARCHAR(50),
	@TinhTrang bit
AS
BEGIN
	DECLARE @MaNhomSach CHAR(10)
	SELECT @MaNhomSach = MaNhomSach FROM dbo.NHOMSACH WHERE TenNhom = @NhomSach	

	UPDATE dbo.SACH SET TenSach = @TenSach, TacGia = @TacGia, GiaNhap = @GiaNhap, GiaBan = @GiaBan, MaNhomSach = @MaNhomSach, TinhTrang = @TinhTrang
	WHERE MaSach = @MaSach
END
GO

-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_UpdateNhaCungCap
	@MaNCC CHAR(10),
	@TenNCC NVARCHAR(50),
	@DienThoai CHAR(10),
	@TinhTrang BIT
AS
BEGIN
	UPDATE dbo.NHACUNGCAP SET TenNCC = @TenNCC, DienThoai = @DienThoai, TinhTrang = @TinhTrang WHERE MaNCC = @MaNCC
END
GO

-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_UpdateNhomSach
	@MaNhomSach CHAR(10),
	@TenNhom NVARCHAR(40),
	@TinhTrang BIT
AS
BEGIN
	UPDATE dbo.NHOMSACH SET TenNhom = @TenNhom, TinhTrang = @TinhTrang
	WHERE MaNhomSach = @MaNhomSach
END

-------------------------------------------------------------------------------------------------------------------------------------------
GO
CREATE FUNCTION F_NewID_NhomKhachHang ()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.NHOMKHACHHANG
	RETURN 'NKH00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_KhachHang ()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.KHACHHANG
	RETURN 'KH00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_HoaDon()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.HoaDon
	RETURN 'HD00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_NhaCungCap()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.NhaCungCap
	RETURN 'NCC00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_NhanVien()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.NHANVIEN
	RETURN 'NV00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_NhomSach()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.NHOMSACH
	RETURN 'NS00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_PhieuNhap()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.PHIEUNHAP
	RETURN 'PN00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

CREATE FUNCTION F_NewID_SACH()
	RETURNS NCHAR(10)
AS 
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.SACH
	RETURN 'S00' + CONVERT(CHAR(10),@dem + 1)
END;
GO

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROC P_ThemKhachHang
	@TenKhachHang NVARCHAR(40),
	@DienThoai CHAR(10),
	@TenNhom NVARCHAR(50),
	@TinhTrang BIT
AS
BEGIN
	DECLARE @MaNhom CHAR(10)
	SELECT @MaNhom = MaNhomKhachHang FROM dbo.NHOMKHACHHANG WHERE TenNhom = @TenNhom

	INSERT INTO dbo.KHACHHANG ( MaKhachHang, TenKhachHang, MaNhomKhachHang, DienThoai, TongTien, TinhTrang )
	VALUES ( dbo.F_NewID_KhachHang(), @TenKhachHang, @MaNhom, @DienThoai, NULL, @TinhTrang )
END
GO

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROC P_ThemNhanVien
	@TenNhanVien NVARCHAR(50),
	@GioiTinh NCHAR(10),
	@NgaySinh DATE,
	@SoCMND CHAR(13),
	@DienThoai CHAR(10),
	@TinhTrang BIT
AS
BEGIN
	INSERT INTO dbo.NHANVIEN
	    ( MaNhanVien, TenNhanVien, GioiTinh, NgaySinh, SoCMND, DienThoai, TinhTrang )
	VALUES
	    ( dbo.F_NewID_NhanVien(), @TenNhanVien, @GioiTinh, @NgaySinh, @SoCMND, @DienThoai, @TinhTrang )
END
GO

--------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_ThemTaiKhoan
	@TenDangNhap CHAR(20),
	@MaNhanVien CHAR(20),
	@MatKhau CHAR(100)
AS
BEGIN
	INSERT INTO dbo.TAIKHOAN ( TenDN, MaNhanVien, MatKhau) VALUES
	    ( @TenDangNhap, @MaNhanVien, @MatKhau)
END
GO

--------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_ThemNhomKhachHang
	@TenNhom NVARCHAR(50),
	@GhiChu NVARCHAR(60)
AS
BEGIN
	INSERT INTO dbo.NHOMKHACHHANG ( MaNhomKhachHang, TenNhom, GhiChu )
	VALUES
	     ( dbo.F_NewID_NhomKhachHang(), @TenNhom, @GhiChu )
END
GO
-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC	P_ThemSach
	@TenSach NVARCHAR(50),
	@TacGia NVARCHAR(50),
	@GiaNhap MONEY ,
	@GiaBan MONEY,
	@NhomSach NVARCHAR(50),
	@TinhTrang bit
AS
BEGIN
	DECLARE @MaNhomSach CHAR(10)
	SELECT @MaNhomSach = MaNhomSach FROM dbo.NHOMSACH WHERE TenNhom = @NhomSach	
	INSERT INTO dbo.SACH ( MaSach, TenSach, TacGia, GiaNhap, GiaBan, MaNhomSach, TinhTrang ) VALUES ( dbo.F_NewID_SACH(), @TenSach, @TacGia, @GiaNhap, @GiaBan, @MaNhomSach, @TinhTrang )
END
GO

-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_ThemNhaCungCap
	@TenNCC NVARCHAR(50),
	@DienThoai CHAR(10),
	@TinhTrang BIT
AS
BEGIN
	INSERT INTO dbo.NHACUNGCAP ( MaNCC, TenNCC, DienThoai, TinhTrang ) VALUES ( dbo.F_NewID_NhaCungCap(), @TenNCC, @DienThoai, @TinhTrang )
END
GO

-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_ThemNhomSach
	@TenNhom NVARCHAR(40),
	@TinhTrang BIT
AS
BEGIN
	INSERT INTO  dbo.NHOMSACH ( MaNhomSach, TenNhom, TinhTrang )
	VALUES
	     ( dbo.F_NewID_NhomSach(), @TenNhom, @TinhTrang )
END
GO
-------------------------------------------------------------------------------------------------------------------------------------------

CREATE PROC P_ThemHoaDon
	@MaNhanVien CHAR(10), @MaKhachHang CHAR(10), @NgayBan DATE
AS
BEGIN
	INSERT INTO dbo.HOADON ( MaHoaDon, MaNhanVien, MaKhachHang, NgayBan )
	VALUES
	     ( dbo.F_NewID_HoaDon(),@MaNhanVien , @MaKhachHang, @NgayBan )
END
GO

CREATE PROC P_ThemChiTietHoaDon
	@MaSach CHAR(10), @DonGia MONEY, @SoLuong INT
AS
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.HoaDon
	DECLARE @MaHoaDon CHAR(10) = 'HD00' + CONVERT(CHAR(10),@dem)

	INSERT INTO dbo.CHITIETHOADON ( MaHoaDon, MaSach, DonGia, SoLuong )
	VALUES
	     (@MaHoaDon,@MaSach , @DonGia, @SoLuong )
END
GO
-------------------------------------------------------------------------------------------------------------------------------------------
CREATE PROC P_ThemPhieuNhap
	@MaNhanVien CHAR(10), @MaNhaCungCap CHAR(10), @NgayNhap DATE
AS
BEGIN
	INSERT INTO dbo.PHIEUNHAP ( MaPhieuNhap, MaNhanVien, NgayNhap, MaNCC )
	VALUES
	     ( dbo.F_NewID_PhieuNhap(),@MaNhanVien , @NgayNhap, @MaNhaCungCap)
END
GO


CREATE PROC P_ThemChiTietPhieuNhap
	@MaSach CHAR(10), @DonGia MONEY, @SoLuong INT
AS
BEGIN
	DECLARE @dem INT;
	SELECT @dem = COUNT(*) FROM  dbo.PHIEUNHAP
	DECLARE @MaPhieuNhap CHAR(10) = 'PN00' + CONVERT(CHAR(10),@dem)

	INSERT INTO dbo.CHITIETPHIEUNHAP ( MaPhieuNhap, MaSach, DonGia, SoLuong )
	VALUES
	     ( @MaPhieuNhap, @MaSach, @DonGia, @SoLuong )
END
GO