-- 注意：该页面对应的前台目录为views/facAuthKey文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023081109088650300', NULL, 'facId认证秘钥', '/facAuthKey/facAuthKeyList', 'facAuthKey/FacAuthKeyList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650301', '2023081109088650300', '添加facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650302', '2023081109088650300', '编辑facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650303', '2023081109088650300', '删除facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650304', '2023081109088650300', '批量删除facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650305', '2023081109088650300', '导出excel_facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081109088650306', '2023081109088650300', '导入excel_facId认证秘钥', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:fac_auth_key:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-11 09:08:30', NULL, NULL, 0, 0, '1', 0);