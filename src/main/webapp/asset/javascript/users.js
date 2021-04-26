function checkid(idField, url) {
	console.log("id filed: ", idField.value);
	console.log("url: ", url);

	if (idField.value.trim().length == 0) {
		alert("아이디를 입력해 주세요");
		return;
	}
	$.ajax({
		url: url,
		type: "GET",
		dataType: "json",
		data: {
			id: idField.value.trim()
		},
		success: function(result) {
			console.log("Result: ", result);
			if (result.data == true) {
				idField.form.check.value = "f";
				let x = document.getElementsByClassName("checkId")[0];
				x.innerText = "다른 아이디로 가입해 주세요";
			} else {
				idField.form.check.value = "t";
				let x = document.getElementsByClassName("checkId")[0];
				x.innerText = "사용할 수 있는 아이디 입니다.";
			}
		},
		error: function(xhr, status, error) {
			console.error("Status: ", status);
			console.error("Responese: ", xhr);
			console.error("error: ", error);

			idField.form.check.value = "f";
		}
	});
}

function checkJoinForm(frm) {
	console.log(frm.agree.checked);
	var userName = frm.userName.value.trim();
	var id = frm.id.value.trim();
	var check = frm.check.value;
	var password = frm.password.value.trim();
	var agree = frm.agree.value.checked;

	if (userName.length == 0) {
		alert("이름을 입력해주세요");
		frm.userName.focus();
	} else if (id.length == 0) {
		alert("아이디를 입력해주세요.");
		frm.id.focus();
	} else if (check == "c") {
		alert("아이디 중복 체크를 해주세요");
	} else if (password.length == 0) {
		alert("패스워드를 입력해주세요.");
		frm.password.focus();
	} else if (!frm.agree.checked) {
		alert("약관에 동의해 주세요.");
	} else {
		frm.submit();
	}
}



function checklogin(frm,  url) {
	var idField = frm.id.value.trim();
	var passwordField = frm.password.value.trim();

	$.ajax({
		url: url,
		type: "GET",
		dataType: "json",
		data: {
			id: idField,
			password: passwordField
		},
		success: function(result) {
			console.log("Result: ", result);
			if (result.data == false) {
				let x = document.getElementsByClassName("falsetext")[0];
				x.innerText = "로그인실패";
				let y = document.getElementsByClassName("falsetext")[1];
				y.innerText = "아이디/패스워드를 확인해 주세요";
			}
			else {
				frm.submit();
			}
		},
		error: function(xhr, status, error) {
			console.error("Status: ", status);
			console.error("Responese: ", xhr);
			console.error("error: ", error);

			idField.form.check.value = "f";
		}
	});
}