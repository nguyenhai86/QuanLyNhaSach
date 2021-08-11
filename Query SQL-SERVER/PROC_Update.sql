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
SELECT * FROM dbo.NHANVIEN
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

SELECT * FROM dbo.TAIKHOAN