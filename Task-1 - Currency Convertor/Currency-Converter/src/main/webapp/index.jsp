<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE-11"/>
		<meta hhtp-equiv="Cache-Controller" content="no-cache"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Currency Converter - WebApp</title>
		<link rel="stylesheet" href="css/index.css?version=?" />
	</head>
	<body>
		<main>
			<div>
				<div id="title">
					<h1>Currency Converter </h1>
				</div>
				<div id="rate-wrpr">
					<h5>1 <span id="rate-country-txt">EUR </span>equals</h5>
					<div>
						<h2 id="curr-rate-text">00.000 INR</h2>
						<span id="fluctns-txt-wrpr"><h4 id="fluctns-txt"></h4></span>
					</div>
				</div>
				<div id="inputs-wrpr">		
		            <span>
		            	<select id="select-1">
		            		<option value="USD">EUR</option>
		            	</select>
		            	<input id="input-1" type="text">
		            </span>
		            <div id="arrow-icon-wrpr">
		            	<div id="cnvrsn-arrow-icon"></div>
		            </div>
		            
		            <span id="inputs-sub-wrpr">
		            	<div>
			            	<select id="select-2">
			            		<option value="INR">INR</option>
			            	</select>
			            	<input id="input-2" type="text" disabled>
		            	</div>
		            </span>
				</div>
				<div>
					<button id="convert-btn">Convert To</button>
				</div>
			</div>
		</main>
		<script src="js/index.js"></script>
	</body>
</html>