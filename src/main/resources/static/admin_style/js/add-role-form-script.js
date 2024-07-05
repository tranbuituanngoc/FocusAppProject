$(document).ready(function () {
    $("#add-role").on('click', function (e) {
        e.preventDefault();
        var name = $('input[name="name"]').val();
        var userPermission = $('#user-permission').is(':checked');
        var rolePermission = $('#role-permission').is(':checked');

        $.ajax({
            url: "/api/nguoi-dung/create-role",
            type: "POST",
            data: JSON.stringify({
                role_name: name,
                user_permission: userPermission,
                role_permission: rolePermission
            }),
            contentType: "application/json",
            success: function (data) {
                Swal.fire("Create role successfully!", "Press Ok to continue.", "success").then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/admin/role";
                    }
                });
            },
            error: function (data) {
                Swal.fire("Create role failed", "Please try again", "error");
            }
        });
    });

    $("#update-role").on('click', function (e) {
        e.preventDefault();
        var name = $('input[name="name"]').val();
        var userPermission = $('#user-permit').is(':checked');
        var rolePermission = $('#role-permit').is(':checked');

        $.ajax({
            url: "/api/nguoi-dung/update-role",
            type: "PUT",
            data: JSON.stringify({
                id: roleId,
                role_name: name,
                user_permission: userPermission,
                role_permission: rolePermission
            }),
            contentType: "application/json",
            success: function (data) {
                Swal.fire("Update successfully!", "Press Ok to continue.", "success").then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/admin/role";
                    }
                });
            },
            error: function (data) {
                Swal.fire("Update failed", "Please try again", "error");
            }
        });
    });

});
$("#form-validation").validate({
    ignore: ':hidden:not(:checkbox)',
    errorElement: 'label',
    errorClass: 'is-invalid',
    validClass: 'is-valid',
    rules: {
        name: {
            required: true
        },
    }
});