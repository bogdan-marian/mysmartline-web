function setRunGuide() {
	setCookie("runGuide", "true", 30);
	startTourIfRunGide();
}
function endRunGuide() {
	setCookie("runGuide", "false", 30);
}

function startTourIfRunGide() {
	var val_runGuide = getCookie("runGuide");
	if (val_runGuide == "true"){
		//set introGuide
		var introguide =  introJs();
		
		if (userLogedIn()=="true"){
			var val_pageNameVal = getCookie("pageNameVal");
			switch (val_pageNameVal) {
			case "tourHome":
				setOptionsTourHome(introguide);
				break;
			case "home":
				setOptionsHome(introguide);
				break;
			case "servicePoints":
				setOptionsSericePoints(introguide);
				break;
			case "lines":
				setOptionsLines(introguide);
				break;
			case "webRegistrationPage":
				setOptionswebRegistrationPage(introguide);
				break;
			}
		}else{
			setOptionsNotLogedIn(introguide);
		}
		
		//start introGuide
		introguide.start();
	}
}

function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function userLogedIn(){
	return getCookie("cookieLogedIn");
}

function setOptionsHome(introguide){
	introguide.setOptions({
		steps : [ {
			element : '.navbar-nav',
			intro : 'Now that you are logged in you should navigate to /Tools/Service-points in order to create at least one service point.'
		}]
	});
}

function setOptionsSericePoints(introguide){
	var rows = getCookie("servicePointRows");
	var contentRows = rows -1;
	if (rows > 1){
		introguide.setOptions({
			steps : [ {
				element : '.table',
				intro : 'The service points are refering to the entities that are interacting with your clients. It is mandatory to have at least one service point. You already have ' + contentRows + " service points active"
			},
			{
				element : '.navbar-nav',
				intro : 'The next step is to create a line. Navigate the menue to Tools / lines.'
			}]
		});
	}else{
		introguide.setOptions({
			steps : [ {
				element : '.createButon',
				intro : 'The service points are refering to the entities that are interacting with your clients. It is mandatory to have at least one service point. Use this button to create one service point.'
			}]
		});
	}
	
}

function setOptionsLines(introguide){
	var lines = getCookie("linesCount");
	
	if (lines > 0){
		introguide.setOptions({
			steps : [ {
				element : '.table',
				intro : 'The lines are refering to the services you are providing for your clients. It is mandatory to have at least one line. You already have ' + lines + " lines active"
			},
			{
				element : '.webRegistration',
				intro : 'In order to test the main metod that your clients can use to register to this line you can use this buton.'
			}]
		});
	}else{
		introguide.setOptions({
			steps : [ {
				element : '.table',
				intro : 'The lines are refering to the services you are providing for your clients. It is mandatory to have at least one line.  ' 
			},
			{
				element : '.createButon',
				intro : 'Use this button to create one line.'
			}]
		});
	}
	
}

function setOptionsTourHome(introguide){
	introguide.setOptions({
		steps : [ {
			element : '.startTourButon',
			intro : 'Use this buton to set the start tour cookie.'
		}, {
			element : '.endTourButon',
			intro : 'second big boton'
		}, {
			element : '.lastTourButon',
			intro : 'And this is the last buton from set options.'
		} ]
	});
}
function setOptionswebRegistrationPage(introguide){
	introguide.setOptions({
		steps : [ {
			element : '.qrcode',
			intro : 'Point your device to this QR code in order to sign up to this line'
		}, {
			element : '.simulateClients',
			intro : 'You can also easily simulate clients from hear'
		}, {
			element : '.notificationPanel',
			intro : 'Use the notification panel to notify your clients about changes in the line status.'
		}, {
			element : '.androidLink',
			intro : 'Install the android app in order to easily enable your client to sign up to the line or configure display panels.'
		} ]
	});
	
	introguide.oncomplete(function() { 
		setCookie("runGuide", "false", 30);
		alert("Congratulations you have compleated the tour")
	    });
}
function setOptionsNotLogedIn(introguide){
	introguide.setOptions({
		steps : [ {
			element : '.loginbar',
			intro : 'First of all you have to log in. Start from hear'
		} 
		]
	});
}