window.onload = function() {
	
	if(document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function() {
            location.href = "/member/regist";
        }
    }
    
    if(document.getElementById("idRedupCheck")) {
		
		const $duplication = document.getElementById("idRedupCheck");
		
		$duplication.onclick = function() {
			
			let memId = document.getElementById("memId").value.trim();
			
			fetch("/member/idRedupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memId: memId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));
		}
		
	}
    
    if(document.getElementById("welcome")) {
        const $regist = document.getElementById("welcome");
        $regist.onclick = function() {
            location.href = "/member/welcome";
        }
    }

    
    if(document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function() {
            location.href = "/member/login";
        }
    }

    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/member/logout";
        }
    }
	
}