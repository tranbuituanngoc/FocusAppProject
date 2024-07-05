$(document).ready(function () {
    $.ajax({
        url: "/api/nguoi-dung/getAllRoleForAdmin",
        type: "GET",
        success: function (data) {
            var select = $('#role-selection');
            select.empty();
            $.each(data, function (index, role) {
                select.append('<option value="' + role.id + '">' + role.roleName + '</option>');
            });
            select.select2();

            if (roleId && roleId !== 'default') {
                select.val(roleId).trigger('change');
            }
        }

    })

    $("#add-user").on('click', function (e) {
        e.preventDefault();
        var name = $('input[name="name"]').val();
        var email = $('input[name="inputEmail"]').val();
        var password = $('input[name="inputPassword"]').val();
        var role = $('#role-selection').val();

        $.ajax({
            url: "/api/nguoi-dung/existEmail",
            type: "GET",
            data: {
                email: email
            },
            success: function (data) {
                if (data == true) {
                    Swal.fire("Email is already exist", "Please try again", "error");
                } else {
                    $.ajax({
                        url: "/api/nguoi-dung/create-user", // Sửa URL API
                        type: "POST",
                        data: JSON.stringify({
                            name: name,
                            email: email,
                            password: password,
                            role_id: role
                        }),
                        contentType: "application/json",
                        success: function (data) {
                            Swal.fire("Create user successfully!", "Press Ok to continue.", "success").then((result) => {
                                if (result.isConfirmed) {
                                    window.location.href = "/admin/home";
                                }
                            });
                        },
                        error: function (data) {
                            Swal.fire("Create user failed", "Please try again", "error");
                        }
                    });
                }
            }
        })
    });

    $("#update-user").on('click', function (e) {
        e.preventDefault();
        var name = $('input[name="name"]').val();
        var email = $('input[name="inputEmail"]').val();
        var role = $('#role-selection').val();

        $.ajax({
            url: "/api/nguoi-dung/update-user",
            type: "PUT",
            data: JSON.stringify({
                id: userId,
                name: name,
                email: email,
                role_id: role
            }),
            contentType: "application/json",
            success: function (data) {
                Swal.fire("Update successfully!", "Press Ok to continue.", "success").then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/admin/home";
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
        inputEmail: {
            required: true,
            email: true,
            pattern: /^[1-9][0-9]{7}@st\.hcmuaf\.edu\.vn$/
        },
        inputPassword: {
            required: true,
            minlength: 8,
            pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z\d]).{8,}$/
        },
        inputPasswordConfirm: {
            required: true,
            equalTo: '#password'
        },
    }
});