// вешаем маску на телефон
$('.phone-field').inputmask("+7(999)999-9999");

// добавляем правило для валидации телефона
jQuery.validator.addMethod("checkMaskPhone", function(value, element) {
    return /\+\d{1}\(\d{3}\)\d{3}-\d{4}/g.test(value);
});

// получаем нашу форму по class
var form = $('.form-reg');

// включаем валидацию в форме
form.validate();

// вешаем валидацию на поле с телефоном по классу
$.validator.addClassRules({
    'phone-field': {
        checkMaskPhone: true,
    }
});
