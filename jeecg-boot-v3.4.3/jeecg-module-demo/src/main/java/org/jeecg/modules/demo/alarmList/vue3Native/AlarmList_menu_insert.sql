-- 注意：该页面对应的前台目录为views/alarmList文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023091002487050200', NULL, '告警列表', '/alarmList/alarmListList', 'alarmList/AlarmListList', NULL, NULL, 0, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050201', '2023091002487050200', '添加告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050202', '2023091002487050200', '编辑告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050203', '2023091002487050200', '删除告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050204', '2023091002487050200', '批量删除告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050205', '2023091002487050200', '导出excel_告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023091002487050206', '2023091002487050200', '导入excel_告警列表', NULL, NULL, 0, NULL, NULL, 2, 'org.jeecg.modules.demo:alarm_list:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-10 14:48:20', NULL, NULL, 0, 0, '1', 0);