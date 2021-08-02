USE QLNS
GO

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
GO
