-- 注意：该页面对应的前台目录为views/workLog文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023081405118150110', NULL, '业务的日志', '/workLog/workLogList', 'workLog/WorkLogList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118150111', '2023081405118150110', '添加业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118160112', '2023081405118150110', '编辑业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118160113', '2023081405118150110', '删除业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118160114', '2023081405118150110', '批量删除业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118160115', '2023081405118150110', '导出excel_业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023081405118170116', '2023081405118150110', '导入excel_业务的日志', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:work_log:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-08-14 17:11:11', NULL, NULL, 0, 0, '1', 0);