-- 注意：该页面对应的前台目录为views/submitInfo文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023081604191420110', NULL, '上报信息', '/submitInfo/submitInfoList', 'submitInfo/SubmitInfoList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191420111', '2023081604191420110', '添加上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191420112', '2023081604191420110', '编辑上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191420113', '2023081604191420110', '删除上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191420114', '2023081604191420110', '批量删除上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191420115', '2023081604191420110', '导出excel_上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081604191430116', '2023081604191420110', '导入excel_上报信息', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:submit_info:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-16 16:19:11', NULL, NULL, 0, 0, '1', 0);