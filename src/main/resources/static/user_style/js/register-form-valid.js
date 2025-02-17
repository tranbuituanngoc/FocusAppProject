$(document).ready(function () {
    $('form').on('submit', function (e) {
        e.preventDefault();

        const email = $('#email').val();
        const password = $('#password').val();
        const fullName = $('#full-name').val();
        const rePassword = $('#re-password').val();

        const emailRegex = /^[1-9][0-9]{7}@st\.hcmuaf\.edu\.vn$/;
        const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z\d]).{8,}$/gm;

        let isValid = true;

        if (!emailRegex.test(email)) {
            $('#error-email').text('Email không hợp lệ');
            isValid = false;
            console.log('email failed')
        } else {
            $('#error-email').text('');
        }

        if (!passwordRegex.test(password)) {
            $('#error-password').html('Mật khẩu phải có tối thiểu 8 ký tự và phải bao gồm<br> 1 kí tự in hoa, 1 kí tự in thường và 1 chữ số');
            isValid = false;
            console.log('password failed')
        } else {
            $('#error-password').text('');
        }

        if (password !== rePassword) {
            $('#error-repassword').text('Mật khẩu nhập lại không khớp');
            isValid = false;
            console.log('repassword  failed')
        } else {
            $('#error-repassword').text('');
        }

        if (!fullName) {
            $('#error-fullname').text('Họ và tên không được để trống');
            isValid = false;
            console.log('fullname failed')
        } else {
            $('#error-fullname').text('');
        }


        if (isValid) {
            console.log('submit')
            this.submit();
        }
    });
});

