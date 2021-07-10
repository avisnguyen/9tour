package xyz.nhatbao.ninetour.util;

import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

public class MessageUtil {
    public Map<String, String> getMessage(String message) {
        Map<String, String> result = new HashMap<String, String>();
        if (message == null) {
            return null;
//            result.put("msg", "Test message!");
//            result.put("alert", "alert-success");
        } else if (message.equals("update_success")) {
            result.put("msg", "Cập nhật thành công!");
            result.put("alert", "alert-success");
        } else if (message.equals("insert_success")) {
            result.put("msg", "Thêm thành công!");
            result.put("alert", "alert-success");
        } else if (message.equals("delete_success")) {
            result.put("msg", "Xóa thành công!");
            result.put("alert", "alert-success");
        } else if (message.equals("error_system")) {
            result.put("msg", "Lỗi hệ thống");
            result.put("alert", "alert-danger");
        } else if (message.equals("email_available")) {
            result.put("msg", "Email đã tồn tại trên hệ thống!");
            result.put("alert", "alert-danger");
        } else if (message.equals("incorrect_password")) {
            result.put("msg", "Mật khẩu cũ không chính xác!");
            result.put("alert", "alert-danger");
        } else if (message.equals("not_permission_self_role_update")) {
            result.put("msg", "Không thể tự cập nhật chức vụ");
            result.put("alert", "alert-warning");
        } else if (message.equals("not_permission_self_active_update")) {
            result.put("msg", "Không thể tự mở/khoá tài khoản");
            result.put("alert", "alert-warning");
        } else if (message.equals("not_permission_update_user")) {
            result.put("msg", "Không thể cập nhật thành viên có quyền bằng hoặc cao hơn bạn!");
            result.put("alert", "alert-danger");
        } else if (message.equals("not_permission_create_user")) {
            result.put("msg", "Không thể thêm thành viên có quyền bằng hoặc cao hơn bạn!");
            result.put("alert", "alert-danger");
        } else if (message.equals("not_permission_delete_user")) {
            result.put("msg", "Không thể xoá một vài thành viên trong danh sách đã chọn!");
            result.put("alert", "alert-warning");
        } else if (message.equals("not_permission_update_user_to_equal")) {
            result.put("msg", "Không thể nâng quyền người dùng lên bằng hoặc cao hơn bạn !");
            result.put("alert", "alert-warning");

        } else if (message.equals("error")) {
            result.put("msg", "Tài khoản hoặc mật khẩu không chính xác !");
            result.put("alert", "alert-danger");
        } else if (message.equals("max_session")) {
            result.put("msg", "Tài khoản của bạn đang được đăng nhập ở thiết bị khác!");
            result.put("alert", "alert-danger");
        } else if (message.equals("timeout")) {
            result.put("msg", "Hết phiên làm việc!");
            result.put("alert", "alert-warning");
        } else if (message.equals("logout_success")) {
            result.put("msg", "Tài khoản của bạn đã đăng xuất!");
            result.put("alert", "alert-warning");
        } else if (message.equals("register_success")) {
            result.put("msg", "Đăng ký tài khoản thành công!");
            result.put("alert", "alert-success");
        } else if (message.equals("refund_success")) {
            result.put("msg", "Yêu cầu hủy tour đã được gửi đi!");
            result.put("alert", "alert-success");
        } else if (message.equals("reset_success")) {
            result.put("msg", "Mật khẩu đã được thay đổi thành công!");
            result.put("alert", "alert-success");
        } else {
            result.put("msg", "Xảy ra lỗi chưa xác định!");
            result.put("alert", "alert-danger");
        }
        return result;
    }
}
