/*==============================================================*/
/* DBMS name:      ORACLE Version 12c                           */
/* Created on:     6/4/2021 6:08:50 PM                          */
/*==============================================================*/


alter table CHITIETDIEN
   drop constraint FK_CHITIETD_DIEN2_HOADON;

alter table CHITIETNUOC
   drop constraint FK_CHITIETN_NUOC2_HOADON;

alter table HOADON
   drop constraint FK_HOADON_CO_PHONGO;

alter table HOADON
   drop constraint FK_HOADON_LAP_NHANVIEN;

alter table HOADON
   drop constraint FK_HOADON_THANHTOAN_SINHVIEN;

alter table NHANVIEN
   drop constraint FK_NHANVIEN_LAM_THONGTIN;

alter table NHANVIEN
   drop constraint FK_NHANVIEN_QUANLI2_PHONGNHA;

alter table NHANVIEN
   drop constraint FK_NHANVIEN_THUOC_PHONGNHA;

alter table PHONGNHANVIEN
   drop constraint FK_PHONGNHA_QUANLI_NHANVIEN;

alter table PHONGO
   drop constraint FK_PHONGO_BAOGOM_TOA;

alter table SINHVIEN
   drop constraint FK_SINHVIEN_HOC_TRUONG;

alter table SINHVIEN
   drop constraint FK_SINHVIEN_LA_THONGTIN;

alter table SINHVIEN
   drop constraint FK_SINHVIEN_O_PHONGO;

alter table TOA
   drop constraint FK_TOA_TRUONGTOA_NHANVIEN;

drop table BANGTHAMSO cascade constraints;

drop table CHITIETDIEN cascade constraints;

drop table CHITIETNUOC cascade constraints;

drop table HOADON cascade constraints;

drop table NHANVIEN cascade constraints;

drop table PHONGNHANVIEN cascade constraints;

drop table PHONGO cascade constraints;

drop table SINHVIEN cascade constraints;

drop table THONGTINCOBAN cascade constraints;

drop table TOA cascade constraints;

drop table TRUONG cascade constraints;

/*==============================================================*/
/* Table: BANGTHAMSO                                            */
/*==============================================================*/
create table BANGTHAMSO (
   GIADIEN              NUMBER,
   GIANUOC              NUMBER
);

/*==============================================================*/
/* Table: CHITIETDIEN                                           */
/*==============================================================*/
create table CHITIETDIEN (
   IDCHITIETDIEN        CHAR(10)              not null,
   IDHOADON             CHAR(12)              not null,
   NGAYBATDAU           DATE,
   NGAYKETTHUC          DATE,
   SODAU                INTEGER,
   SOCUOI               INTEGER,
   TONG                 NUMBER,
   constraint PK_CHITIETDIEN primary key (IDCHITIETDIEN)
);

/*==============================================================*/
/* Table: CHITIETNUOC                                           */
/*==============================================================*/
create table CHITIETNUOC (
   IDCHITIETNUOC        CHAR(10)              not null,
   IDHOADON             CHAR(12)              not null,
   NGAYBATDAU           DATE,
   NGAYKETTHUC          DATE,
   SODAU                INTEGER,
   SOCUOI               INTEGER,
   TONG                 NUMBER,
   constraint PK_CHITIETNUOC primary key (IDCHITIETNUOC)
);

/*==============================================================*/
/* Table: HOADON                                                */
/*==============================================================*/
create table HOADON (
   IDHOADON             CHAR(12)              not null,
   IDNHANVIEN           CHAR(5)               not null,
   IDPHONGO             CHAR(8)               not null,
   IDSINHVIEN           CHAR(10)              not null,
   NGAYLAP              DATE,
   NGAYTHU              DATE,
   LOAI                 CHAR(30),
   TRIGIA               NUMBER,
   GHICHU               VARCHAR2(30),
   constraint PK_HOADON primary key (IDHOADON)
);

/*==============================================================*/
/* Table: NHANVIEN                                              */
/*==============================================================*/
create table NHANVIEN (
   IDNHANVIEN           CHAR(5)               not null,
   IDPHONGNHANVIEN      CHAR(4)               not null,
   QUANLIPHONG          CHAR(4),
   ID                   CHAR(10)              not null,
   NGAYVAOLAM           DATE,
   TRANGTHAI            NUMBER(1),
   constraint PK_NHANVIEN primary key (IDNHANVIEN)
);

/*==============================================================*/
/* Table: PHONGNHANVIEN                                         */
/*==============================================================*/
create table PHONGNHANVIEN (
   IDPHONGNHANVIEN      CHAR(4)               not null,
   IDQUANLI             CHAR(5)               not null,
   TENPHONG             VARCHAR2(30),
   VITRI                VARCHAR2(20),
   constraint PK_PHONGNHANVIEN primary key (IDPHONGNHANVIEN)
);

/*==============================================================*/
/* Table: PHONGO                                                */
/*==============================================================*/
create table PHONGO (
   IDPHONGO             CHAR(8)               not null,
   IDTOA                CHAR(4)               not null,
   COSOVATCHAT          VARCHAR2(100),
   LOAIPHONG            VARCHAR2(10),
   SONGUOIO             NUMBER(1),
   CONTRONG             NUMBER(1),
   constraint PK_PHONGO primary key (IDPHONGO)
);

/*==============================================================*/
/* Table: SINHVIEN                                              */
/*==============================================================*/
create table SINHVIEN (
   IDSINHVIEN           CHAR(10)              not null,
   ID                   CHAR(10)              not null,
   IDPHONGO             CHAR(8)               not null,
   IDTRUONG             CHAR(10)              not null,
   NGAYVAO              DATE,
   NGAYHETHAN           DATE,
   TRANGTHAI            NUMBER(1),
   constraint PK_SINHVIEN primary key (IDSINHVIEN)
);

/*==============================================================*/
/* Table: THONGTINCOBAN                                         */
/*==============================================================*/
create table THONGTINCOBAN (
   ID                   CHAR(10)              not null,
   HO                   NVARCHAR2(30),
   TEN                  NVARCHAR2(10),
   DIACHI               VARCHAR2(100),
   GIOITINH             NUMBER(1),
   NGAYSINH             DATE,
   SDT                  CHAR(11),
   EMAIL                VARCHAR2(50),
   CMND                 CHAR(12),
   QUOCTICH             CHAR(30),
   DANTOC               VARCHAR2(25),
   BHYT                 CHAR(16),
   TENTHANNHAN          NVARCHAR2(40),
   SDTTHANNHAN          CHAR(11),
   DIACHITHANNHAN       VARCHAR2(100),
   HINHANH              BLOB,
   constraint PK_THONGTINCOBAN primary key (ID)
);

/*==============================================================*/
/* Table: TOA                                                   */
/*==============================================================*/
create table TOA (
   IDTOA                CHAR(4)               not null,
   IDNHANVIEN           CHAR(5)               not null,
   TENTOA               VARCHAR2(15),
   LOAITOA              NUMBER(1),
   constraint PK_TOA primary key (IDTOA)
);

/*==============================================================*/
/* Table: TRUONG                                                */
/*==============================================================*/
create table TRUONG (
   IDTRUONG             CHAR(10)              not null,
   TENTRUONG            VARCHAR2(50),
   constraint PK_TRUONG primary key (IDTRUONG)
);
-- Cai nay de loai bo phan c## trong username
-- ALTER SESSION SET "_ORACLE_SCRIPT" = true;

ALTER SESSION SET NLS_DATE_FORMAT = 'MM DD YYYY';

drop role r_SinhVien;
create role r_SinhVien;
grant connect to r_SinhVien;
grant select, update on ThongTinCoBan to r_SinhVien;
grant select, update on SinhVien to r_SinhVien;

drop role r_NhanVien;
create role r_NhanVien;
grant connect to r_NhanVien;
grant select, update on ThongTinCoBan to r_NhanVien;
grant select, update on NhanVien to r_NhanVien;

drop role r_QuanLiPhongNhanVien;
create role r_QuanLiPhongNhanVien;
grant select, update, delete on PhongNhanVien to r_QuanLiPhongNhanVien;

drop role r_BanQuanLi;
create role r_BanQuanLi;
grant connect, dba, resource, oem_monitor to r_BanQuanLi;
grant create session to r_BanQuanLi;

Insert into ThongTinCoBan values('0','??ng Quang','Quý','Phù Cát, Bình ??nh','0','02/08/1986','0358286932','quydq@gmail.com','242484989','Vi?t Nam','Ch?m','DN1544567890007','H??ng','0348951692','Phù Cát, Bình ??nh',null);
Insert into ThongTinCoBan values('1','??ng Quang ','Quy?n','Phù Cát, Bình ??nh','0','11/23/1994','0576797187','quyendq@yahoo.com','273078851','Vi?t Nam','C? Ho','DN1644567190206','Tr??ng','0745159064','Phù Cát, Bình ??nh',null);
Insert into ThongTinCoBan values('2','Tr?n Quang','Kh?','Qu?ng Ngãi','0','05/01/1990','0324984228','khatq@gmail.com','266949452','Vi?t Nam','Phù Lá','DN2778456783210','Cai','0170906724','Qu?ng Ngãi',null);
Insert into ThongTinCoBan values('3','Hu?nh Anh','Ki?t','B?n Tre','0','10/10/1985','0433110455','kietha@ui.edu.vn','209625763','Vi?t Nam','Ê ?ê','DN3223086181010','Th?','0844634630','B?n Tre',null);
Insert into ThongTinCoBan values('4','Hà Hoàng','Giang','B?c Ninh','0','05/06/1998','0868274950','gianghh@gmail.com','000987593843','Vi?t Nam','Ch?m','DN5309793159241','Khái','0983921180','B?c Ninh',null);
Insert into ThongTinCoBan values('5','Nguy?n','Khiêm','V?nh Long','0','05/02/1988','0892515804','khiemn@hcmut.edu.vn','223095811','Vi?t Nam','Kinh','DN7618387318529','Anh','0320886592','V?nh Long',null);
Insert into ThongTinCoBan values('6','Hoàng Long','?t','V?nh Long','0','03/14/1997','0585779266','10006@ktx.com','000475201011','Vi?t Nam','Ê ?ê','DN2790117562348','Ð?i','0771347879','V?nh Long',null);
Insert into ThongTinCoBan values('7','Hu?nh Long','B?n','??ng Nai','0','12/02/1999','0944118238','10007@ktx.com','000831485645','Vi?t Nam','M?','DN4246213775839','Trát','0519992126','??ng Nai',null);
Insert into ThongTinCoBan values('8','Hoàng V?n','Xuy?n','Long An','0','10/20/1987','0586968045','10008@ktx.com','000713078380','Vi?t Nam','Sán Chay','DN6552867226436','Hán','0354667157','Long An',null);
Insert into ThongTinCoBan values('9','Tr?n Th?','Ch?nh','Hòa Bình','1','01/18/1996','0816753454','10009@ktx.com','000162922753','Vi?t Nam','Tà Ôi','DN1754108752560','Nhu','0301603466','Hòa Bình',null);
Insert into ThongTinCoBan values('10','Lê Th?','Tr?c','Bình ??nh','1','07/08/1987','0966405427','10010@ktx.com','000747434353','Vi?t Nam','Xinh Mun','DN3786667097055','H?ng','0866639783','Bình ??nh',null);
Insert into ThongTinCoBan values('11','Hoàng Th?','Ðính','Thái Bình','1','03/28/1997','0197914405','10011@ktx.com','000555882970','Vi?t Nam','Hrê','DN8206158099821','H?o','0951329553','Thái Bình',null);
Insert into ThongTinCoBan values('12','Phan Uy','Nho','B?c Giang','0','04/30/1992','0161778327','10012@ktx.com','000991593545','Vi?t Nam','Giáy','DN2782906207649','Kh?i','0950816620','B?c Giang',null);
Insert into ThongTinCoBan values('13','Lê Th?','Thu?n','Tây Ninh','1','09/20/1997','0673588713','10013@ktx.com','000799911220','Vi?t Nam','Mnông','DN7964234674699','L?ng','0388129904','Tây Ninh',null);
Insert into ThongTinCoBan values('14','Bùi Th?','Thao','Hòa Bình','1','10/08/1985','0567240144','10014@ktx.com','000307950727','Vi?t Nam','Hà Nhì','DN1539893016716','Nhung','0870944968','Hòa Bình',null);
Insert into ThongTinCoBan values('15','??ng V?n','Ðông','Qu?ng Bình','0','10/01/1994','0822018354','10015@ktx.com','000652149236','Vi?t Nam','Si La','DN8136353909889','L?i','0169710241','Qu?ng Bình',null);
Insert into ThongTinCoBan values('16','Hoàng V?n','X??ng','Trà Vinh','0','03/04/1989','0809882457','10016@ktx.com','000793437893','Vi?t Nam','C? Lao','DN5412161731647','Gi?n','0815016135','Trà Vinh',null);
Insert into ThongTinCoBan values('17','Phan Anh','B?i','Bình Thu?n','0','12/24/1995','0333091216','10017@ktx.com','000309750820','Vi?t Nam','Hrê','DN5120263819607','Kính','0307867132','Bình Thu?n',null);
Insert into ThongTinCoBan values('18','Võ Th?','C?ng','Th?a Thiên Hu?','1','05/18/1995','0206415324','10018@ktx.com','000602854427','Vi?t Nam','Stiêng','DN8098646233045','Khoa','0674096073','Th?a Thiên Hu?',null);
Insert into ThongTinCoBan values('19','Lê Th?','Ch??ng','C?n Th?','1','01/09/1999','0885539773','10019@ktx.com','000113326291','Vi?t Nam','M??ng','DN9637336049935','Khá','0419445995','C?n Th?',null);
Insert into ThongTinCoBan values('20','Tr?n Th?','S?c','Ninh Bình','1','12/29/1987','0839930603','10020@ktx.com','000232929248','Vi?t Nam','C?ng','DN2491792682580','An','0414286430','Ninh Bình',null);
Insert into ThongTinCoBan values('10000','Hoàng ?ình','Phú','??k L?k','0','02/10/2001','0751004993','19520838@gm.uit.edu.vn','241819304','Vi?t Nam','Sán Chay','SV4456975074213','Giá','0680961500','??k L?k',utl_raw.cast_to_raw('Phu.jpg'));
Insert into ThongTinCoBan values('10001','V?n Qu?c','Tr?nh','Gia Lai','0','03/20/2000','0157204220','18521538@ms.uit.edu.vn','219460447','Vi?t Nam','Brâu','SV5073919520815','Ch?','0523888456','Gia Lai',utl_raw.cast_to_raw('Trinh.jpg'));
Insert into ThongTinCoBan values('10002','Tr??ng Nguy?n Tr??ng','Duy','Long An','0','04/10/2001','0578213064','19521437@ms.uit.edu.vn','000356630234','Vi?t Nam','C?ng','SV5690863967416','Quang','0339889897','Long An',utl_raw.cast_to_raw('Duy.jpg'));
Insert into ThongTinCoBan values('10003','??ng H?u','Ch??ng','Qu?ng Nam','0','07/19/1998','0147250507','16521495@gm.uit.edu.vn','000893367768','Vi?t Nam','Ch? Ro','SV3223086181010','Chi?m','0120442805','Qu?ng Nam',utl_raw.cast_to_raw('Chuong.jpg'));
Insert into ThongTinCoBan values('10004','Ph?m Qu?c','Hùng','TP.H? Chí Minh','0','07/20/2001','0736191348','19521579@gm.uit.edu.vn','000443953197','Vi?t Nam','Kháng','SV3840030627612','Trâm','0801713733','TP.H? Chí Minh',utl_raw.cast_to_raw('Hung.jpg'));
Insert into ThongTinCoBan values('10005','V? Nh?','L?','Hà N?i','0','12/22/2001','0539553866','levn@yahoo.com','000162919497','Vi?t Nam','Kh? mú','SV4456975074213','Hoài','0673843451','Hà N?i',null);
Insert into ThongTinCoBan values('10006','Ph?m Th?','Ph??ng','Qu?ng Ninh','1','04/23/2001','0476658290','phuongpt@gmail.com','000308443837','Vi?t Nam','C? Tu','SV5073919520815','Song','0259113188','Qu?ng Ninh',null);
Insert into ThongTinCoBan values('10007','Nguy?n Th? Minh ','Th?','Bình Ph??c','0','06/13/2001','0633090635','thunmt@gmail.com','000922966158','Vi?t Nam','Dao','SV5690863967416','Ð?ng','0910846207','Bình Ph??c',null);
Insert into ThongTinCoBan values('10008','?? Th?','Th?y','Ea Kar, ??k L?k','1','04/13/2001','0687913544','thuydt@gmail.com','000314129232','Vi?t Nam','Kh? mú','SV6307808414018','Ðô','0116217934','Ea Kar, ??k L?k',null);
Insert into ThongTinCoBan values('10009','Hoàng Quang','Nghiêm','Kiên Giang','0','08/03/1999','0766530294','20009@sv.ktx.com','000523254700','Vi?t Nam','Si La','SV9001778346835','Tr?m','0818637276','Kiên Giang',null);
Insert into ThongTinCoBan values('10010','Ngô Thái','Hoán','Lào Cai','0','09/10/1998','0783905921','20010@sv.ktx.com','000948690029','Vi?t Nam','M??ng','SV5506776498824','Li?n','0261107366','Lào Cai',null);
Insert into ThongTinCoBan values('10011','Ngô Nh?t','H?u','Thành ph? H? Chí Minh','0','10/15/2001','0623108053','20011@sv.ktx.com','000523682611','Vi?t Nam','Bru - Vân Ki?u','SV7639956097590','B?c','0309275164','Thành ph? H? Chí Minh',null);
Insert into ThongTinCoBan values('10012','H? Th?','To?i','Lâm ??ng','1','11/22/2000','0471320389','20012@sv.ktx.com','000361100009','Vi?t Nam','Hà Nhì','SV5421178902414','D?t','0721429001','Lâm ??ng',null);
Insert into ThongTinCoBan values('10013','Hoàng Anh','Th?a','??ng Tháp','0','07/09/1998','0686898901','20013@sv.ktx.com','000110436909','Vi?t Nam','Pà Th?n','SV4756019850897','Ti?t','0106575048','??ng Tháp',null);
Insert into ThongTinCoBan values('10014','Võ Th?','B?ng','Lâm ??ng','1','11/23/2002','0309325261','20014@sv.ktx.com','000748505874','Vi?t Nam','H Mông','SV7204971367559','B?ng','0509363488','Lâm ??ng',null);
Insert into ThongTinCoBan values('10015','Ngô H?u','T??ng','V?nh Phúc','0','01/05/2001','0732773981','20015@sv.ktx.com','000967115665','Vi?t Nam','B? Y','SV2764710818876','Ði?m','0137066753','V?nh Phúc',null);
Insert into ThongTinCoBan values('10016','Ngô Th?','Nh?n','Cao B?ng ','1','01/22/2002','0267137514','20016@sv.ktx.com','000428713518','Vi?t Nam','Chu Ru','SV4420945850660','T?m','0496749325','Cao B?ng ',null);
Insert into ThongTinCoBan values('10017','Phan Nh?t','Mão','B?c Liêu','0','08/19/2000','0341608368','20017@sv.ktx.com','000908897504','Vi?t Nam','La Ha','SV8487210188824','Mây','0465811357','B?c Liêu',null);
Insert into ThongTinCoBan values('10018','Lê Long','Ðôn','??ng Tháp','0','12/05/1999','0719230232','20018@sv.ktx.com','000893095564','Vi?t Nam','C? Ho','SV5691688594740','Sang','0163528875','??ng Tháp',null);
Insert into ThongTinCoBan values('10019','Lê Long','Nh??ng','??ng Nai','0','06/16/2001','0655406919','20019@sv.ktx.com','000607508759','Vi?t Nam','Ch?t','SV5444438560880','Khoát','0808386675','??ng Nai',null);
Insert into ThongTinCoBan values('10020','Bùi Uy','Ð?c','S?n La','0','10/13/1998','0596645114','20020@sv.ktx.com','000566505666','Vi?t Nam','M?','SV2196150380791','Th?nh','0547277489','S?n La',null);
Insert into ThongTinCoBan values('10021','Hu?nh ?ình','Khuy?n','Trà Vinh','0','10/25/1999','0302393831','20021@sv.ktx.com','000582441446','Vi?t Nam','Kinh','SV7078419545243','Nghi?p','0664691665','Trà Vinh',null);
Insert into ThongTinCoBan values('10022','V? Th?','Th??c','Ti?n Giang','1','06/26/2001','0288819398','20022@sv.ktx.com','000910890134','Vi?t Nam','X? ??ng','SV7092223819979','H?u','0347625539','Ti?n Giang',null);
Insert into ThongTinCoBan values('10023','Phan Long','N??ng','Bà r?a – V?ng tàu','0','05/11/2001','0300192317','20023@sv.ktx.com','000464302085','Vi?t Nam','C? Tu','SV2229228775728','S?nh','0461579138','Bà r?a – V?ng tàu',null);
Insert into ThongTinCoBan values('10024','?? Huy','Trinh','Th?a Thiên Hu?','0','03/11/2002','0446315711','20024@sv.ktx.com','000633543571','Vi?t Nam','Ê ?ê','SV5890976455108','M?o','0129418785','Th?a Thiên Hu?',null);
Insert into ThongTinCoBan values('10025','Nguy?n Th?','Tr?nh','Qu?ng Ngãi','1','03/21/2000','0107334152','20025@sv.ktx.com','000855899523','Vi?t Nam','Hoa','SV1233656577868','C?','0363832583','Qu?ng Ngãi',null);
Insert into ThongTinCoBan values('10026','Phan Uy','Chúa','Ngh? An','0','09/16/2000','0646437224','20026@sv.ktx.com','000910435713','Vi?t Nam','Ra Glai','SV1458067188244','Phi?t','0776108997','Ngh? An',null);
Insert into ThongTinCoBan values('10027','Phan Th?','Tr?ng','Lào Cai','1','10/11/2002','0331731839','20027@sv.ktx.com','000716747994','Vi?t Nam','Ng??i Bahnar','SV1073056631613','Dao','0887949542','Lào Cai',null);
Insert into ThongTinCoBan values('10028','Võ Th?','Huynh','Tuyên Quang','1','06/21/1999','0975042914','20028@sv.ktx.com','000423378040','Vi?t Nam','La Ha','SV5836735957090','H??ng','0607649687','Tuyên Quang',null);
Insert into ThongTinCoBan values('10029','??ng H?u','Phòng','Thanh Hóa','0','07/13/2002','0450105387','20029@sv.ktx.com','000305774394','Vi?t Nam','H Mông','SV9748760424408','Khiêu','0560773863','Thanh Hóa',null);
Insert into ThongTinCoBan values('10030','Phan Nh?t','Tuy','H?ng Yên','0','12/20/1999','0494030209','20030@sv.ktx.com','000858468592','Vi?t Nam','Thái','SV8394796367839','Nh?','0297774730','H?ng Yên',null);
Insert into ThongTinCoBan values('10031','Hu?nh ?ình','Buông','H?i Phòng','0','03/03/2001','0683954829','20031@sv.ktx.com','000411257309','Vi?t Nam','Si La','SV2261989062275','Kh?','0291492866','H?i Phòng',null);
Insert into ThongTinCoBan values('10032','Ngô Nh?t','Ph?','Qu?ng Nam','0','12/06/1999','0126061170','20032@sv.ktx.com','000856981392','Vi?t Nam','Hoa','SV1354439609085','L?p','0778509942','Qu?ng Nam',null);
Insert into ThongTinCoBan values('10033','Nguy?n Nh?t','L?p','B?c Ninh','0','10/25/1998','0410487380','20033@sv.ktx.com','000148900377','Vi?t Nam','La Ha','SV9294311917420','L?c','0404459536','B?c Ninh',null);
Insert into ThongTinCoBan values('10034','Phan V?n','Hi?u','??k Nông','0','08/25/1998','0171453364','20034@sv.ktx.com','000296398290','Vi?t Nam','Lào','SV1475662703674','Y?n','0313870323','??k Nông',null);
Insert into ThongTinCoBan values('10035','Võ V?n','L?p','Tuyên Quang','0','10/25/1999','0840404953','20035@sv.ktx.com','000110533222','Vi?t Nam','B? Y','SV4432860641979','Ð?n','0769497270','Tuyên Quang',null);
Insert into ThongTinCoBan values('10036','Bùi Huy','Giác','V?nh Long','0','12/04/2002','0689359527','20036@sv.ktx.com','000808445281','Vi?t Nam','Pu Péo','SV7196739919597','Cú','0958764140','V?nh Long',null);
Insert into ThongTinCoBan values('10037','Võ Long','Th?','Sóc Tr?ng','0','12/29/1999','0344683912','20037@sv.ktx.com','000214520511','Vi?t Nam','Kinh','SV1802632500873','Nhung','0725733874','Sóc Tr?ng',null);
Insert into ThongTinCoBan values('10038','V? Huy','Tính','Thanh Hóa','0','12/13/1999','0286218386','20038@sv.ktx.com','000805774139','Vi?t Nam','Giáy','SV1729429560243','To?n','0614584767','Thanh Hóa',null);
Insert into ThongTinCoBan values('10039','Bùi Th?','Ng?','Tây Ninh','1','05/06/1999','0933375284','20039@sv.ktx.com','000881557888','Vi?t Nam','Nùng','SV6756787915440','B?ch','0376821188','Tây Ninh',null);
Insert into ThongTinCoBan values('10040','H? Thái','Thu?n','H?ng Yên','0','01/11/2000','0296197653','20040@sv.ktx.com','000983310430','Vi?t Nam','M?','SV3972160305220','Thu','0982563628','H?ng Yên',null);
Insert into ThongTinCoBan values('10041','Hu?nh Th?','Phiên','Lào Cai','1','11/10/1999','0698964264','20041@sv.ktx.com','000969704979','Vi?t Nam','Stiêng','SV9163021930432','Tri','0260340967','Lào Cai',null);
Insert into ThongTinCoBan values('10042','Lê ?ình','Thiên','Bình Thu?n','0','03/25/2000','0948227461','20042@sv.ktx.com','000742044358','Vi?t Nam','Phù Lá','SV4441016011179','Cúc','0158173648','Bình Thu?n',null);
Insert into ThongTinCoBan values('10043','Lý Th?','Tám','Hà Giang','1','01/31/2002','0676322609','20043@sv.ktx.com','000876157537','Vi?t Nam','La H?','SV7866227234053','Phòng','0371853564','Hà Giang',null);
Insert into ThongTinCoBan values('10044','H? Th?','T?','??ng Tháp','1','07/10/2002','0248857049','20044@sv.ktx.com','000917980476','Vi?t Nam','M??ng','SV4152154120130','V?ng','0983314448','??ng Tháp',null);
Insert into ThongTinCoBan values('10045','Bùi ?ình','L?ng','??ng Tháp','0','05/23/2000','0365123262','20045@sv.ktx.com','000182516764','Vi?t Nam','Ê ?ê','SV3808585058043','Song','0432873205','??ng Tháp',null);
Insert into ThongTinCoBan values('10046','Võ Th?','Dinh','?à N?ng','1','10/31/1999','0732421934','20046@sv.ktx.com','000648560428','Vi?t Nam','Ch? Ro','SV9648148564322','Ti?m','0503110001','?à N?ng',null);
Insert into ThongTinCoBan values('10047','Lý Anh','Ð??c','C?n Th?','0','03/19/1998','0988712479','20047@sv.ktx.com','000574062566','Vi?t Nam','C? Tu','SV7056163016477','L?','0522905188','C?n Th?',null);
Insert into ThongTinCoBan values('10048','Bùi Thái','K?nh','Ti?n Giang','0','03/25/2000','0979971641','20048@sv.ktx.com','000742830504','Vi?t Nam','Mnông','SV1479185273628','Quýnh','0201870273','Ti?n Giang',null);
Insert into ThongTinCoBan values('10049','Lý Th?','B?ng','H?ng Yên','1','05/08/2001','0817550281','20049@sv.ktx.com','000785850783','Vi?t Nam','Lào','SV9346494416685','L?a','0483940993','H?ng Yên',null);
Insert into ThongTinCoBan values('10050','?? Uy','Ph?c','Qu?ng Ngãi','0','01/27/2002','0565143545','20050@sv.ktx.com','000491504765','Vi?t Nam','Thái','SV5155394686717','Bôn','0857682193','Qu?ng Ngãi',null);

Drop user "242484989";Create user "242484989" identified by "111111";Grant r_NhanVien to "242484989";
Drop user "273078851";Create user "273078851" identified by "111111";Grant r_NhanVien to "273078851";
Drop user "266949452";Create user "266949452" identified by "111111";Grant r_NhanVien to "266949452";
Drop user "209625763";Create user "209625763" identified by "111111";Grant r_NhanVien to "209625763";
Drop user "000987593843";Create user "000987593843" identified by "111111";Grant r_NhanVien to "000987593843";
Drop user "223095811";Create user "223095811" identified by "111111";Grant r_NhanVien to "223095811";
Drop user "000475201011";Create user "000475201011" identified by "111111";Grant r_NhanVien to "000475201011";
Drop user "000831485645";Create user "000831485645" identified by "111111";Grant r_NhanVien to "000831485645";
Drop user "000713078380";Create user "000713078380" identified by "111111";Grant r_NhanVien to "000713078380";
Drop user "000162922753";Create user "000162922753" identified by "111111";Grant r_NhanVien to "000162922753";
Drop user "000747434353";Create user "000747434353" identified by "111111";Grant r_NhanVien to "000747434353";
Drop user "000555882970";Create user "000555882970" identified by "111111";Grant r_NhanVien to "000555882970";
Drop user "000991593545";Create user "000991593545" identified by "111111";Grant r_NhanVien to "000991593545";
Drop user "000799911220";Create user "000799911220" identified by "111111";Grant r_NhanVien to "000799911220";
Drop user "000307950727";Create user "000307950727" identified by "111111";Grant r_NhanVien to "000307950727";
Drop user "000652149236";Create user "000652149236" identified by "111111";Grant r_NhanVien to "000652149236";
Drop user "000793437893";Create user "000793437893" identified by "111111";Grant r_NhanVien to "000793437893";
Drop user "000309750820";Create user "000309750820" identified by "111111";Grant r_NhanVien to "000309750820";
Drop user "000602854427";Create user "000602854427" identified by "111111";Grant r_NhanVien to "000602854427";
Drop user "000113326291";Create user "000113326291" identified by "111111";Grant r_NhanVien to "000113326291";
Drop user "000232929248";Create user "000232929248" identified by "111111";Grant r_SinhVien to "000232929248";
Drop user "241819304";Create user "241819304" identified by "111111";Grant r_SinhVien to "241819304";
Drop user "219460447";Create user "219460447" identified by "111111";Grant r_SinhVien to "219460447";
Drop user "000356630234";Create user "000356630234" identified by "111111";Grant r_SinhVien to "000356630234";
Drop user "000893367768";Create user "000893367768" identified by "111111";Grant r_SinhVien to "000893367768";
Drop user "000443953197";Create user "000443953197" identified by "111111";Grant r_SinhVien to "000443953197";
Drop user "000162919497";Create user "000162919497" identified by "111111";Grant r_SinhVien to "000162919497";
Drop user "000308443837";Create user "000308443837" identified by "111111";Grant r_SinhVien to "000308443837";
Drop user "000922966158";Create user "000922966158" identified by "111111";Grant r_SinhVien to "000922966158";
Drop user "000314129232";Create user "000314129232" identified by "111111";Grant r_SinhVien to "000314129232";
Drop user "000523254700";Create user "000523254700" identified by "111111";Grant r_SinhVien to "000523254700";
Drop user "000948690029";Create user "000948690029" identified by "111111";Grant r_SinhVien to "000948690029";
Drop user "000523682611";Create user "000523682611" identified by "111111";Grant r_SinhVien to "000523682611";
Drop user "000361100009";Create user "000361100009" identified by "111111";Grant r_SinhVien to "000361100009";
Drop user "000110436909";Create user "000110436909" identified by "111111";Grant r_SinhVien to "000110436909";
Drop user "000748505874";Create user "000748505874" identified by "111111";Grant r_SinhVien to "000748505874";
Drop user "000967115665";Create user "000967115665" identified by "111111";Grant r_SinhVien to "000967115665";
Drop user "000428713518";Create user "000428713518" identified by "111111";Grant r_SinhVien to "000428713518";
Drop user "000908897504";Create user "000908897504" identified by "111111";Grant r_SinhVien to "000908897504";
Drop user "000893095564";Create user "000893095564" identified by "111111";Grant r_SinhVien to "000893095564";
Drop user "000607508759";Create user "000607508759" identified by "111111";Grant r_SinhVien to "000607508759";
Drop user "000566505666";Create user "000566505666" identified by "111111";Grant r_SinhVien to "000566505666";
Drop user "000582441446";Create user "000582441446" identified by "111111";Grant r_SinhVien to "000582441446";
Drop user "000910890134";Create user "000910890134" identified by "111111";Grant r_SinhVien to "000910890134";
Drop user "000464302085";Create user "000464302085" identified by "111111";Grant r_SinhVien to "000464302085";
Drop user "000633543571";Create user "000633543571" identified by "111111";Grant r_SinhVien to "000633543571";
Drop user "000855899523";Create user "000855899523" identified by "111111";Grant r_SinhVien to "000855899523";
Drop user "000910435713";Create user "000910435713" identified by "111111";Grant r_SinhVien to "000910435713";
Drop user "000716747994";Create user "000716747994" identified by "111111";Grant r_SinhVien to "000716747994";
Drop user "000423378040";Create user "000423378040" identified by "111111";Grant r_SinhVien to "000423378040";
Drop user "000305774394";Create user "000305774394" identified by "111111";Grant r_SinhVien to "000305774394";
Drop user "000858468592";Create user "000858468592" identified by "111111";Grant r_SinhVien to "000858468592";
Drop user "000411257309";Create user "000411257309" identified by "111111";Grant r_SinhVien to "000411257309";
Drop user "000856981392";Create user "000856981392" identified by "111111";Grant r_SinhVien to "000856981392";
Drop user "000148900377";Create user "000148900377" identified by "111111";Grant r_SinhVien to "000148900377";
Drop user "000296398290";Create user "000296398290" identified by "111111";Grant r_SinhVien to "000296398290";
Drop user "000110533222";Create user "000110533222" identified by "111111";Grant r_SinhVien to "000110533222";
Drop user "000808445281";Create user "000808445281" identified by "111111";Grant r_SinhVien to "000808445281";
Drop user "000214520511";Create user "000214520511" identified by "111111";Grant r_SinhVien to "000214520511";
Drop user "000805774139";Create user "000805774139" identified by "111111";Grant r_SinhVien to "000805774139";
Drop user "000881557888";Create user "000881557888" identified by "111111";Grant r_SinhVien to "000881557888";
Drop user "000983310430";Create user "000983310430" identified by "111111";Grant r_SinhVien to "000983310430";
Drop user "000969704979";Create user "000969704979" identified by "111111";Grant r_SinhVien to "000969704979";
Drop user "000742044358";Create user "000742044358" identified by "111111";Grant r_SinhVien to "000742044358";
Drop user "000876157537";Create user "000876157537" identified by "111111";Grant r_SinhVien to "000876157537";
Drop user "000917980476";Create user "000917980476" identified by "111111";Grant r_SinhVien to "000917980476";
Drop user "000182516764";Create user "000182516764" identified by "111111";Grant r_SinhVien to "000182516764";
Drop user "000648560428";Create user "000648560428" identified by "111111";Grant r_SinhVien to "000648560428";
Drop user "000574062566";Create user "000574062566" identified by "111111";Grant r_SinhVien to "000574062566";
Drop user "000742830504";Create user "000742830504" identified by "111111";Grant r_SinhVien to "000742830504";
Drop user "000785850783";Create user "000785850783" identified by "111111";Grant r_SinhVien to "000785850783";
Drop user "000491504765";Create user "000491504765" identified by "111111";Grant r_SinhVien to "000491504765";

Insert into BangThamSo values('1678','5485');
Insert into BangThamSo values('1734','10557');
Insert into BangThamSo values('2014','11799');
Insert into BangThamSo values('2536','');
Insert into BangThamSo values('2834','');
Insert into BangThamSo values('2927','');

Insert into ChiTietDien values('D00000001','HDDN00000001','01/01/2021','02/01/2021','4562','4661','182734');
Insert into ChiTietDien values('D00000002','HDDN00000002','01/01/2021','02/01/2021','4253','4343','166122');
Insert into ChiTietDien values('D00000003','HDDN00000003','01/01/2021','02/01/2021','4215','4310','175351');
Insert into ChiTietDien values('D00000004','HDDN00000004','01/01/2021','02/01/2021','4256','4350','173505');
Insert into ChiTietDien values('D00000005','HDDN00000005','01/01/2021','02/01/2021','3959','4058','182734');
Insert into ChiTietDien values('D00000006','HDDN00000006','01/01/2021','02/01/2021','4786','4877','167968');
Insert into ChiTietDien values('D00000007','HDDN00000007','01/01/2021','02/01/2021','4536','4629','171659');
Insert into ChiTietDien values('D00000008','HDDN00000008','01/01/2021','02/01/2021','4369','4489','222728');
Insert into ChiTietDien values('D00000009','HDDN00000009','01/01/2021','02/01/2021','4253','4365','207469');
Insert into ChiTietDien values('D00000010','HDDN00000010','01/01/2021','02/01/2021','4251','4383','245617');
Insert into ChiTietDien values('D00000011','HDDN00000011','01/01/2021','02/01/2021','3968','4093','232265');

Insert into HoaDon values('HDP00000001','10018','110A1','20000','05/01/2020','05/01/2020','Hóa ??n phòng','2300000','');
Insert into HoaDon values('HDP00000002','10001','111A1','20002','05/01/2020','05/01/2020','Hóa ??n phòng','2300000','');
Insert into HoaDon values('HDP00000003','10013','112A1','20006','05/01/2020','05/01/2020','Hóa ??n phòng','3000000','');
Insert into HoaDon values('HDP00000004','10004','210A1','20012','05/01/2020','05/02/2020','Hóa ??n phòng','2300000','');
Insert into HoaDon values('HDP00000005','10007','211A1','20020','05/01/2020','05/02/2020','Hóa ??n phòng','2300000','');
Insert into HoaDon values('HDDN00000001','10013','110A1','20000','04/02/2020','01/01/2020','Hóa ??n ?i?n n??c','243069','');
Insert into HoaDon values('HDDN00000002','10014','111A1','20002','04/02/2020','01/05/2020','Hóa ??n ?i?n n??c','275822','');
Insert into HoaDon values('HDDN00000003','10014','112A1','20006','04/02/2020','01/05/2020','Hóa ??n ?i?n n??c','257626','');
Insert into HoaDon values('HDDN00000004','10002','210A1','20012','04/02/2020','01/04/2020','Hóa ??n ?i?n n??c','261265','');
Insert into HoaDon values('HDDN00000005','10010','211A1','20020','04/02/2020','01/02/2020','Hóa ??n ?i?n n??c','286949','');
Insert into HoaDon values('HDDN00000006','10016','212A1','20022','04/02/2020','01/01/2020','Hóa ??n ?i?n n??c','294033','');
Insert into HoaDon values('HDDN00000007','10011','110A2','20026','04/02/2020','01/03/2020','Hóa ??n ?i?n n??c','311684','');
Insert into HoaDon values('HDDN00000008','10005','111A2','20032','04/02/2020','01/02/2020','Hóa ??n ?i?n n??c','327892','');
Insert into HoaDon values('HDDN00000009','10018','112A2','20040','04/02/2020','01/07/2020','Hóa ??n ?i?n n??c','369390','');
Insert into HoaDon values('HDDN00000010','10009','210A2','20042','04/02/2020','01/02/2020','Hóa ??n ?i?n n??c','334468','');
Insert into HoaDon values('HDDN00000011','10018','211A2','20046','04/02/2020','01/05/2020','Hóa ??n ?i?n n??c','283258','');

Insert into ChiTietNuoc values('N00000001','HDDN00000001','01/01/2020','02/01/2020','2758','2772','60335');
Insert into ChiTietNuoc values('N00000002','HDDN00000002','01/01/2020','02/01/2020','2563','2583','109700');
Insert into ChiTietNuoc values('N00000003','HDDN00000003','01/01/2020','02/01/2020','3452','3467','82275');
Insert into ChiTietNuoc values('N00000004','HDDN00000004','01/01/2020','02/01/2020','3569','3585','87760');
Insert into ChiTietNuoc values('N00000005','HDDN00000005','01/01/2020','02/01/2020','2563','2582','104215');
Insert into ChiTietNuoc values('N00000006','HDDN00000006','01/01/2020','02/01/2020','2153','2165','65820');
Insert into ChiTietNuoc values('N00000007','HDDN00000007','01/01/2020','02/01/2020','3458','3481','126155');
Insert into ChiTietNuoc values('N00000008','HDDN00000008','01/01/2020','02/01/2020','2135','2148','71305');
Insert into ChiTietNuoc values('N00000009','HDDN00000009','01/01/2020','02/01/2020','3256','3275','104215');
Insert into ChiTietNuoc values('N00000010','HDDN00000010','01/01/2020','02/01/2020','2589','2604','82275');
Insert into ChiTietNuoc values('N00000011','HDDN00000011','01/01/2020','02/01/2020','3457','3482','137125');

Insert into SinhVien values('20000','10000','110A1','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20001','10001','110A1','DHQT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20002','10002','111A1','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20003','10003','111A1','DHYD','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20004','10004','111A1','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20005','10005','111A1','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20006','10006','112A1','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20007','10007','112A1','DHQT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20008','10008','112A1','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20009','10009','112A1','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20010','10010','112A1','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20011','10011','112A1','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20012','10012','210A1','DHYD','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20013','10013','210A1','DHNL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20014','10014','210A1','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20015','10015','210A1','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20016','10016','210A1','DHYD','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20017','10017','210A1','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20018','10018','210A1','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20019','10019','210A1','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20020','10020','211A1','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20021','10021','211A1','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20022','10022','212A1','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20023','10023','212A1','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20024','10024','212A1','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20025','10025','212A1','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20026','10026','110A2','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20027','10027','110A2','DHNL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20028','10028','110A2','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20029','10029','110A2','DHQT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20030','10030','110A2','DHNL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20031','10031','110A2','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20032','10032','111A2','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20033','10033','111A2','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20034','10034','111A2','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20035','10035','111A2','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20036','10036','111A2','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20037','10037','111A2','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20038','10038','111A2','DHCNTT','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20039','10039','111A2','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20040','10040','112A2','DHBK','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20041','10041','112A2','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20042','10042','210A2','DHYD','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20043','10043','210A2','DHYD','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20044','10044','210A2','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20045','10045','210A2','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20046','10046','211A2','CDNH','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20047','10047','211A2','DHKTL','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20048','10048','211A2','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20049','10049','211A2','DHKHXHVNV','01/01/2021','12/31/2021','0');
Insert into SinhVien values('20050','10050','211A2','DHKHXHVNV','01/01/2021','12/31/2021','0');

insert into PhongO values('110A1','A1','1 bàn, 1 gh?','Th??ng','2','0');
insert into PhongO values('111A1','A1','1 bàn, 1 gh?','Th??ng','4','0');
insert into PhongO values('112A1','A1','1 bàn, 1 gh?','Vip','6','0');
insert into PhongO values('210A1','A1','2 bàn, 2 gh?','Vip','8','0');
insert into PhongO values('211A1','A1','2 bàn, 2 gh?','Th??ng','2','0');
insert into PhongO values('212A1','A1','2 bàn, 2 gh?','Th??ng','4','0');
insert into PhongO values('110A2','A2','1 bàn, 1 gh?','Vip','6','0');
insert into PhongO values('111A2','A2','1 bàn, 1 gh?','Vip','8','0');
insert into PhongO values('112A2','A2','1 bàn, 1 gh?','Th??ng','2','0');
insert into PhongO values('210A2','A2','2 bàn, 2 gh?','Th??ng','4','0');
insert into PhongO values('211A2','A2','2 bàn, 2 gh?','Vip','6','2');
insert into PhongO values('212A2','A2','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('110A3','A3','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('111A3','A3','1 bàn, 1 gh?','Th??ng','4','4');
insert into PhongO values('112A3','A3','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('210A3','A3','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('211A3','A3','2 bàn, 2 gh?','Th??ng','2','2');
insert into PhongO values('212A3','A3','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('110B1','B1','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('111B1','B1','1 bàn, 1 gh?','Vip','8','8');
insert into PhongO values('112B1','B1','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('210B1','B1','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('211B1','B1','2 bàn, 2 gh?','Vip','6','6');
insert into PhongO values('212B1','B1','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('110B2','B2','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('111B2','B2','1 bàn, 1 gh?','Th??ng','4','4');
insert into PhongO values('112B2','B2','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('210B2','B2','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('211B2','B2','2 bàn, 2 gh?','Th??ng','2','2');
insert into PhongO values('212B2','B2','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('110B3','B3','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('111B3','B3','1 bàn, 1 gh?','Vip','8','8');
insert into PhongO values('112B3','B3','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('210B3','B3','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('211B3','B3','2 bàn, 2 gh?','Vip','6','6');
insert into PhongO values('212B3','B3','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('110BA1','BA1','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('111BA1','BA1','1 bàn, 1 gh?','Th??ng','4','4');
insert into PhongO values('112BA1','BA1','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('210BA1','BA1','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('211BA1','BA1','2 bàn, 2 gh?','Th??ng','2','2');
insert into PhongO values('212BA1','BA1','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('110BA2','BA2','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('111BA2','BA2','1 bàn, 1 gh?','Vip','8','8');
insert into PhongO values('112BA2','BA2','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('210BA2','BA2','2 bàn, 2 gh?','Th??ng','4','4');
insert into PhongO values('211BA2','BA2','2 bàn, 2 gh?','Vip','6','6');
insert into PhongO values('212BA2','BA2','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('110BA3','BA3','1 bàn, 1 gh?','Th??ng','2','2');
insert into PhongO values('111BA3','BA3','1 bàn, 1 gh?','Th??ng','4','4');
insert into PhongO values('112BA3','BA3','1 bàn, 1 gh?','Vip','6','6');
insert into PhongO values('210BA3','BA3','2 bàn, 2 gh?','Vip','8','8');
insert into PhongO values('211BA3','BA3','2 bàn, 2 gh?','Th??ng','2','2');
insert into PhongO values('212BA3','BA3','2 bàn, 2 gh?','Th??ng','4','4');

Insert into NhanVien values('10000','YT','','0','02/23/2006','1');
Insert into NhanVien values('10001','TT','','1','02/06/2018','1');
Insert into NhanVien values('10002','TT','','2','06/03/2016','1');
Insert into NhanVien values('10003','BV','','3','08/28/2005','1');
Insert into NhanVien values('10004','TC','','4','07/17/2022','1');
Insert into NhanVien values('10005','TT','','5','07/21/2009','1');
Insert into NhanVien values('10006','TT','','6','10/21/2020','1');
Insert into NhanVien values('10007','YT','','7','01/29/2026','1');
Insert into NhanVien values('10008','TT','','8','05/21/2014','1');
Insert into NhanVien values('10009','YT','','9','09/20/2016','1');
Insert into NhanVien values('10010','YT','','10','11/01/2011','1');
Insert into NhanVien values('10011','TC','','11','11/09/2020','1');
Insert into NhanVien values('10012','TT','','12','07/23/2014','1');
Insert into NhanVien values('10013','BV','','13','03/24/2021','1');
Insert into NhanVien values('10014','TT','','14','06/22/2004','1');
Insert into NhanVien values('10015','TT','','15','08/12/2020','1');
Insert into NhanVien values('10016','BV','','16','02/29/2012','1');
Insert into NhanVien values('10017','BV','','17','07/21/2014','1');
Insert into NhanVien values('10018','YT','','18','12/06/2018','1');
Insert into NhanVien values('10019','TC','','19','06/28/2022','1');
Insert into NhanVien values('10020','TT','','20','03/09/2015','1');

Insert into PhongNhanVien values('YT','10016','Y t?','Phòng y t?');
Insert into PhongNhanVien values('BV','10001','B?o v?','C?ng');
Insert into PhongNhanVien values('TC','10002','Tài Chính','T?ng 1 tòa A1');
Insert into PhongNhanVien values('TT','10004','Truy?n thông','T?ng 2 tòa BA1');

grant r_QuanLiPhongNhanVien to "273078851";
grant r_QuanLiPhongNhanVien to "266949452";
grant r_QuanLiPhongNhanVien to "000987593843";
grant r_QuanLiPhongNhanVien to "000793437893";

grant r_BanQuanLi to "000987593843";
grant r_BanQuanLi to "000475201011";
grant r_BanQuanLi to "000555882970";
grant r_BanQuanLi to "000799911220";
grant r_BanQuanLi to "000799911220";
grant r_BanQuanLi to "000799911220";
grant r_BanQuanLi to "000799911220";
grant r_BanQuanLi to "000799911220";

Insert into Toa values('A1','10004','Tòa A1','0');
Insert into Toa values('A2','10013','Tòa A2','1');
Insert into Toa values('A3','10011','Tòa A3','0');
Insert into Toa values('B1','10014','Tòa B1','1');
Insert into Toa values('B2','10006','Tòa B2','0');
Insert into Toa values('B3','10015','Tòa B3','1');
Insert into Toa values('BA1','10017','Tòa BA1','0');
Insert into Toa values('BA2','10020','Tòa BA2','1');
Insert into Toa values('BA3','10014','Tòa BA3','0');

Insert into Truong values('DHCNTT','??i h?c Công ngh? thông tin');
Insert into Truong values('DHKHXHVNV','??i h?c Khoa h?c Xã h?i và Nhân v?n');
Insert into Truong values('DHQT','??i h?c Qu?c t?');
Insert into Truong values('DHBK','??i h?c Bách khoa');
Insert into Truong values('DHKTL','??i h?c Kinh t? Lu?t');
Insert into Truong values('DHYD','??i h?c Y d??c');
Insert into Truong values('DHNL','??i h?c Nông lâm');
Insert into Truong values('CDNH','Cao ??ng ngân hàng');

alter table CHITIETDIEN
   add constraint FK_CHITIETD_DIEN2_HOADON foreign key (IDHOADON)
      references HOADON (IDHOADON);

alter table CHITIETNUOC
   add constraint FK_CHITIETN_NUOC2_HOADON foreign key (IDHOADON)
      references HOADON (IDHOADON);

alter table HOADON
   add constraint FK_HOADON_CO_PHONGO foreign key (IDPHONGO)
      references PHONGO (IDPHONGO);

alter table HOADON
   add constraint FK_HOADON_LAP_NHANVIEN foreign key (IDNHANVIEN)
      references NHANVIEN (IDNHANVIEN);

alter table HOADON
   add constraint FK_HOADON_THANHTOAN_SINHVIEN foreign key (IDSINHVIEN)
      references SINHVIEN (IDSINHVIEN);

alter table NHANVIEN
   add constraint FK_NHANVIEN_LAM_THONGTIN foreign key (ID)
      references THONGTINCOBAN (ID);

alter table NHANVIEN
   add constraint FK_NHANVIEN_QUANLI2_PHONGNHA foreign key (IDPHONGNHANVIEN)
      references PHONGNHANVIEN (IDPHONGNHANVIEN);

alter table NHANVIEN
   add constraint FK_NHANVIEN_THUOC_PHONGNHA foreign key (QUANLIPHONG)
      references PHONGNHANVIEN (IDPHONGNHANVIEN);

alter table PHONGNHANVIEN
   add constraint FK_PHONGNHA_QUANLI_NHANVIEN foreign key (IDQUANLI)
      references NHANVIEN (IDNHANVIEN);

alter table PHONGO
   add constraint FK_PHONGO_BAOGOM_TOA foreign key (IDTOA)
      references TOA (IDTOA);

alter table SINHVIEN
   add constraint FK_SINHVIEN_HOC_TRUONG foreign key (IDTRUONG)
      references TRUONG (IDTRUONG);

alter table SINHVIEN
   add constraint FK_SINHVIEN_LA_THONGTIN foreign key (ID)
      references THONGTINCOBAN (ID);

alter table SINHVIEN
   add constraint FK_SINHVIEN_O_PHONGO foreign key (IDPHONGO)
      references PHONGO (IDPHONGO);

alter table TOA
   add constraint FK_TOA_TRUONGTOA_NHANVIEN foreign key (IDNHANVIEN)
      references NHANVIEN (IDNHANVIEN);

