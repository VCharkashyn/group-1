var form = document.getElementById("reg-form");
form.noValidate = true;
form.onsubmit = validateForm;

function validateForm(event) {     
    event = (event ? event : window.event);
    var form = (event.target ? event.target : event.srcElement);
	var formvalid = false;
 
    for (var f = 0; f < form.elements.length; f++) {
      var field = form.elements[f];

        if (field.nodeName !== "INPUT" && field.nodeName !== "TEXTAREA" && field.nodeName !== "SELECT") {
			continue;
		}
 
        if (typeof field.willValidate !== "undefined") {
            if (field.nodeName === "INPUT" && field.type !== field.getAttribute("type")) {
                field.setCustomValidity(LegacyValidation(field) ? "" : "error");
            }			
            field.checkValidity();
        }
        else {
            field.validity = field.validity || {};
            field.validity.valid = LegacyValidation(field);
        }
		
		if (field.name==="name" && field.nodeName==="INPUT") {
			var value = field.value;
			if (value && value.trim().length < 3) {			
				alert("The field name can not be less than 3 characters");	
				field.focus();				
				field.validity.valid = false;
			}	
		}
		
 
        if (!field.validity.valid) { 
			formvalid = false;
        }        
    }
    // cancel form submit if validation fails
    if (!formvalid) {
        if (event.preventDefault) {
		event.preventDefault();
		}
    }
    return formvalid;
}
  
// basic legacy validation checking
function LegacyValidation(field) { 
    var
        valid = true,
        val = field.value,
        type = field.getAttribute("type"),
        chkbox = (type === "checkbox" || type === "radio"),
        required = field.getAttribute("required"),
        minlength = field.getAttribute("minlength"),
        maxlength = field.getAttribute("maxlength"),
        pattern = field.getAttribute("pattern");
 
    // disabled fields should not be validated
    if (field.disabled) return valid;
 
    // value required?
    valid = valid && (!required ||
        (chkbox && field.checked) ||
        (!chkbox && val !== "")
    );
 
    // minlength or maxlength set?
    valid = valid && (chkbox || (
        (!minlength || val.length >= minlength) &&
        (!maxlength || val.length <= maxlength)
    ));
 
    // test pattern
    if (valid && pattern) {
        pattern = new RegExp(pattern);
        valid = pattern.test(val);
    }
 
    return valid;
}