-- 注意：该页面对应的前台目录为views/signalUpload文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023082109535800210', NULL, '信令上传', '/signalUpload/signalUploadList', 'signalUpload/SignalUploadList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535800211', '2023082109535800210', '添加信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535810212', '2023082109535800210', '编辑信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535810213', '2023082109535800210', '删除信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535810214', '2023082109535800210', '批量删除信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535810215', '2023082109535800210', '导出excel_信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023082109535810216', '2023082109535800210', '导入excel_信令上传', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:signal_upload:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-21 09:53:21', NULL, NULL, 0, 0, '1', 0);