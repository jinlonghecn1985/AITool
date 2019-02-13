-- Create table
create table TB_ORGAN
(
  org_id            NUMBER(6),
  org_no            NVARCHAR2(32),
  org_name          NVARCHAR2(128),
  full_name         NVARCHAR2(512),
  orgp_id           NUMBER(6),
  isvalid           NUMBER(1),
  org_level         NUMBER(1),
  orpo_id           NUMBER(8),
  org_type          NVARCHAR2(16),
  org_property      NVARCHAR2(32),
  org_person        NVARCHAR2(16),
  org_person_name   NVARCHAR2(64),
  business_reg_no   VARCHAR2(32),
  business_reg_name VARCHAR2(32),
  legal_person      VARCHAR2(32),
  org_address       VARCHAR2(32),
  business_higher   VARCHAR2(32),
  financal_higher   VARCHAR2(32),
  isnew             NUMBER(1),
  isvip             NUMBER(1),
  gmt_create        DATE,
  gmt_modified      DATE
)
tablespace DSI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TB_ORGAN.org_id
  is '机构部门ID';
comment on column TB_ORGAN.org_no
  is '机构部门编号';
comment on column TB_ORGAN.org_name
  is '机构部门名称';
comment on column TB_ORGAN.orgp_id
  is '上级部门ID';
comment on column TB_ORGAN.isvalid
  is '是否有效';
comment on column TB_ORGAN.org_level
  is '层级';
comment on column TB_ORGAN.orpo_id
  is '变更前ID';
comment on column TB_ORGAN.org_type
  is '机构类型商务=1,客服=2,大客户=3,技术=4,行政=5,财务=6,人资=8,品质=9';
comment on column TB_ORGAN.org_property
  is '机构性质';
comment on column TB_ORGAN.org_person
  is '机构负责人';
comment on column TB_ORGAN.org_person_name
  is '机构负责人姓名';
comment on column TB_ORGAN.business_reg_no
  is '工商注册号';
comment on column TB_ORGAN.business_reg_name
  is '工商注册名';
comment on column TB_ORGAN.legal_person
  is '法人代表';
comment on column TB_ORGAN.org_address
  is '机构所在地';
comment on column TB_ORGAN.business_higher
  is '业务上级部门';
comment on column TB_ORGAN.financal_higher
  is '财务上级部门';
comment on column TB_ORGAN.isnew
  is '是否新部门 Y or N ';
comment on column TB_ORGAN.isvip
  is 'VIP部门';
comment on column TB_ORGAN.gmt_create
  is '添加时间';
comment on column TB_ORGAN.gmt_modified
  is '最后修订时间';


-- Create table
create table TB_EMPLOYEE
(
  empl_id           NUMBER(8),
  empl_no           VARCHAR2(8),
  org_id            NUMBER(6),
  real_name         NVARCHAR2(32),
  isactive          NUMBER(1) default 0 not null,
  grade             VARCHAR2(8),
  rank              VARCHAR2(8),
  job               VARCHAR2(8),
  ext               VARCHAR2(16),
  emplo_id          NUMBER(8),
  mobile            VARCHAR2(16),
  family            NVARCHAR2(32),
  familymobile      VARCHAR2(16),
  lead_id           NUMBER(9),
  lead_no           VARCHAR2(8),
  email             VARCHAR2(64),
  sex               VARCHAR2(4),
  nation            VARCHAR2(128),
  household         NVARCHAR2(128),
  household_address NVARCHAR2(128),
  live_address      NVARCHAR2(128),
  home_address      NVARCHAR2(256),
  education         NVARCHAR2(32),
  education_img     NVARCHAR2(256),
  birthday          DATE,
  idcard            NVARCHAR2(32),
  idcard_img        NVARCHAR2(256),
  idcard_address    NVARCHAR2(256),
  photo             NVARCHAR2(256),
  entry_date        DATE,
  positive_date     DATE,
  quit_date         DATE,
  state             NUMBER(1),
  dimission_over    NUMBER(1),
  dimission_time    DATE,
  gmt_create        DATE,
  gmt_modified      DATE
)
tablespace DSI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TB_EMPLOYEE.empl_id
  is '员工ID';
comment on column TB_EMPLOYEE.empl_no
  is '员工工号';
comment on column TB_EMPLOYEE.org_id
  is '机构ID';
comment on column TB_EMPLOYEE.real_name
  is '真实姓名';
comment on column TB_EMPLOYEE.isactive
  is '是否启用是否有效  0在职在岗 1在职不在岗 3离职退休等不在岗 4删除状态';
comment on column TB_EMPLOYEE.grade
  is '级别编号';
comment on column TB_EMPLOYEE.rank
  is '等级编号';
comment on column TB_EMPLOYEE.job
  is '岗位编号';
comment on column TB_EMPLOYEE.ext
  is '分机号';
comment on column TB_EMPLOYEE.emplo_id
  is '原ID';
comment on column TB_EMPLOYEE.mobile
  is '联系电话';
comment on column TB_EMPLOYEE.family
  is '家庭成员紧急联系人';
comment on column TB_EMPLOYEE.familymobile
  is '紧急联系人电话';
comment on column TB_EMPLOYEE.lead_id
  is '直接上级员工ID';
comment on column TB_EMPLOYEE.lead_no
  is '直接上级员工工号';
comment on column TB_EMPLOYEE.email
  is '邮件地址';
comment on column TB_EMPLOYEE.sex
  is '性别';
comment on column TB_EMPLOYEE.nation
  is '民族';
comment on column TB_EMPLOYEE.household
  is '籍贯';
comment on column TB_EMPLOYEE.household_address
  is '户口所在地';
comment on column TB_EMPLOYEE.live_address
  is '现住址';
comment on column TB_EMPLOYEE.home_address
  is '家庭地址';
comment on column TB_EMPLOYEE.education
  is '最高学历';
comment on column TB_EMPLOYEE.education_img
  is '最高学历扫描图片路径';
comment on column TB_EMPLOYEE.birthday
  is '出生日期';
comment on column TB_EMPLOYEE.idcard
  is '第二代身份证号';
comment on column TB_EMPLOYEE.idcard_img
  is '身份证扫描图片路径';
comment on column TB_EMPLOYEE.idcard_address
  is '身份证地址';
comment on column TB_EMPLOYEE.photo
  is '员工照片路径';
comment on column TB_EMPLOYEE.entry_date
  is '入职日期';
comment on column TB_EMPLOYEE.positive_date
  is '转正日期';
comment on column TB_EMPLOYEE.quit_date
  is '离职日期';
comment on column TB_EMPLOYEE.state
  is '状态1在职2离职';
comment on column TB_EMPLOYEE.dimission_over
  is '是否已处理离职';
comment on column TB_EMPLOYEE.dimission_time
  is '离职处理时间';
comment on column TB_EMPLOYEE.gmt_create
  is '添加时间';
comment on column TB_EMPLOYEE.gmt_modified
  is '最后修订时间';

-- Create table
create table TB_SYNC_HISTORY
(
  his_batch  VARCHAR2(16),
  his_type   VARCHAR2(16),
  lsource    VARCHAR2(16),
  his_result NUMBER(1),
  his_note   VARCHAR2(256),
  his_from   VARCHAR2(2048),
  his_to     VARCHAR2(2048),
  his_dt     DATE
)
tablespace DSI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TB_SYNC_HISTORY.his_batch
  is '批次';
comment on column TB_SYNC_HISTORY.his_type
  is '类型';
comment on column TB_SYNC_HISTORY.lsource
  is '来源';
comment on column TB_SYNC_HISTORY.his_result
  is '结果0正常1失败';
comment on column TB_SYNC_HISTORY.his_note
  is '说明';
comment on column TB_SYNC_HISTORY.his_from
  is '源数据';
comment on column TB_SYNC_HISTORY.his_to
  is '目标数据';
comment on column TB_SYNC_HISTORY.his_dt
  is '时间';



-- Create table
create table TB_UDI_LINKS
(
  lsource VARCHAR2(16),
  udiid   NUMBER(9),
  linksid VARCHAR2(16)
)
tablespace DSI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TB_UDI_LINKS.lsource
  is '来源';
comment on column TB_UDI_LINKS.udiid
  is '系统标识';
comment on column TB_UDI_LINKS.linksid
  is '关联标识';


-- Create table
create table TB_DICTIONARY
(
  pgroup VARCHAR2(8),
  pcode  VARCHAR2(32),
  pvalue VARCHAR2(32)
)
tablespace DSI
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TB_DICTIONARY.pgroup
  is '组别';
comment on column TB_DICTIONARY.pcode
  is '代码';
comment on column TB_DICTIONARY.pvalue
  is '值';


-- Create sequence 
create sequence SEQ_EMPL_ORGAN_ID
minvalue 100
maxvalue 999999
start with 101
increment by 1
cache 5;

create sequence SEQ_EMPL_ID
minvalue 1
maxvalue 999999
start with 1001
increment by 1
cache 20;


insert into TB_DICTIONARY (pgroup, pcode, pvalue) values ('dis', 'sso', '7066A40F427769CC43347AA96B72931A');
insert into TB_DICTIONARY (pgroup, pcode, pvalue) values ('dis', 'ccjava', '7066A40F427769CC43347AA96B72931A');
insert into TB_DICTIONARY (pgroup, pcode, pvalue) values ('dis', 'cmp', '7066A40F427769CC43347AA96B72931A');
insert into TB_DICTIONARY (pgroup, pcode, pvalue) values ('dis', 'erp', '7066A40F427769CC43347AA96B72931A');
commit;