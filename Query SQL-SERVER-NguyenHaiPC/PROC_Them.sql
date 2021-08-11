USE QLNS
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

EXEC dbo.P_ThemChiTietHoaDon @MaSach = '', @DonGia = NULL, @SoLuong = 0
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