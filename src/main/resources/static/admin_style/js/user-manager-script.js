$(document).ready(function () {
    $.ajax({
        url: "/api/nguoi-dung/getAllUserForAdmin",
        type: "GET",
        success: function (data) {
            var table = $('<table id="data-table" class="table">');
            var thead = $('<thead>');
            var tbody = $('<tbody>');
            var headerRow = $('<tr>');
            headerRow.append('<th>Họ và tên</th>');
            headerRow.append('<th>Khoa</th>');
            headerRow.append('<th>Loại tài khoản</th>');
            headerRow.append('<th>Xác thực</th>');
            headerRow.append('<th></th>');
            headerRow.append('<th></th>');
            thead.append(headerRow);
            table.append(thead);

            $.each(data, function (index, user) {
                var row = $('<tr>');
                row.append('<td>' + user.name + '</td>');
                row.append('<td>' + user.department + '</td>');
                row.append('<td>' + user.type_account + '</td>');
                row.append('<td>' + user.verify + '</td>');
                row.append('<td style="text-align: center"><a class="btn btn-icon btn-primary btn-rounded btn-tone" href="/admin/user-form/' + user.id + '" style="display: flex; align-items: center; justify-content: center;"><i class="anticon anticon-edit"></i></a></td>');
                row.append('<td style="text-align: center"><button id="' + user.id + '"  class="btn btn-icon btn-delete btn-primary btn-rounded btn-tone"><i class="anticon anticon-delete"></i></button></td>');
                tbody.append(row);
            });

            table.append(tbody);
            $('#data-user-table').html(table);
            $('#data-table').DataTable();
        }
    });

    $(document).on('click', '.btn-delete', function() {
        var userId = $(this).attr('id');
        var row = $(this).closest('tr');
        Swal.fire({
            title: 'Bạn có chắc chắn muốn xóa người dùng này không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Có',
            cancelButtonText: 'Không'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: "/api/nguoi-dung/delete-user/" + userId,
                    type: "DELETE",
                    success: function() {
                        Swal.fire('Đã xóa!', 'Người dùng đã được xóa.', 'success');
                        row.remove();
                    },
                    error: function() {
                        Swal.fire('Lỗi!', 'Không thể xóa người dùng.', 'error');
                    }
                });
            }
        });
    });

});