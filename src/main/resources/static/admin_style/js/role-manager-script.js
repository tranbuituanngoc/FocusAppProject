$(document).ready(function () {
    $.ajax({
        url: "/api/nguoi-dung/getAllRoleForAdmin",
        type: "GET",
        success: function (data) {
            var table = $('<table id="data-table" class="table">');
            var thead = $('<thead>');
            var tbody = $('<tbody>');
            var headerRow = $('<tr>');
            headerRow.append('<th>Tên quyền</th>');
            headerRow.append('<th>Quản lý tài khoản</th>');
            headerRow.append('<th>Quản lý quyền hạn</th>');
            headerRow.append('<th></th>');
            headerRow.append('<th></th>');
            thead.append(headerRow);
            table.append(thead);

            $.each(data, function (index, role) {
                var row = $('<tr>');
                row.append('<td>' + role.roleName + '</td>');
                row.append('<td>' + (role.userPermission == true ? "Có" : "Không") + '</td>');
                row.append('<td>' + (role.rolePermission == true ? "Có" : "Không") + '</td>');
                row.append('<td style="text-align: center"><a class="btn btn-icon btn-primary btn-rounded btn-tone" href="/admin/role-form/' + role.id + '" style="display: flex; align-items: center; justify-content: center;"><i class="anticon anticon-edit"></i></a></td>');
                row.append('<td style="text-align: center"><button id="' + role.id + '"  class="btn btn-icon btn-delete btn-primary btn-rounded btn-tone"><i class="anticon anticon-delete"></i></button></td>');
                tbody.append(row);
            });

            table.append(tbody);
            $('#role-data-table').html(table);
            $('#data-table').DataTable();
        }
    });
    $(document).on('click', '.btn-delete', function() {
        var roleId = $(this).attr('id');
        var row = $(this).closest('tr');
        Swal.fire({
            title: 'Bạn có chắc chắn muốn xóa quyền hạn này không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Có',
            cancelButtonText: 'Không'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "/api/nguoi-dung/delete-role/" + roleId,
                    type: "DELETE",
                    success: function() {
                        Swal.fire('Đã xóa!', 'Quyền hạn đã được xóa.', 'success');
                        row.remove();
                    },
                    error: function() {
                        Swal.fire('Lỗi!', 'Không thể xóa quyền hạn này.', 'error');
                    }
                });
            }
        });
    });
});