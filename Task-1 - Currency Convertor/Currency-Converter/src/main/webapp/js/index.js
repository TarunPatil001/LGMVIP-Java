// function triggers on load of webpage
window.onload = () => {
  window.sessionStorage.removeItem('page');
  getLatestData('EUR', 'INR');
  value_provided_1.value = 1;
}

// Instance
let convert_to_btn = document.getElementById('convert-btn');
let selected_item_1 = document.getElementById('select-1');
let selected_item_2 = document.getElementById('select-2');
let value_provided_1 = document.getElementById('input-1');
let value_provided_2 = document.getElementById('input-2');
let curr_rate_text = document.getElementById('curr-rate-text');
let rate_country_txt = document.getElementById('rate-country-txt');
let fluctns_txt = document.getElementById('fluctns-txt');
let fluctns_txt_wrpr = document.getElementById('fluctns-txt-wrpr');
let arrow_img = document.getElementById('cnvrsn-arrow-icon');

let rates = {};
let fluctuations = {};

// EventListeners Instance
selected_item_1.addEventListener('change', select_changed);
selected_item_2.addEventListener('change', select_changed);
convert_to_btn.addEventListener('click', convert_btn_clicked);
arrow_img.addEventListener('click', cnvrsn_arrow_icon_clicked);
value_provided_1.addEventListener('keypress', function(e){input_1_handler(e)});

// Event Handlers
function select_changed(){
  getLatestData(selected_item_1.value, selected_item_2.value);
  setCurrentRates(convert(value_provided_1.value, selected_item_1.value, selected_item_2.value), selected_item_1.value, selected_item_2.value);
  setConvertedValue(convert(value_provided_1.value, selected_item_1.value, selected_item_2.value), selected_item_1.value, selected_item_2.value);
}

function convert_btn_clicked(){
  setConvertedValue(convert(value_provided_1.value, selected_item_1.value, selected_item_2.value));
}

function cnvrsn_arrow_icon_clicked(){
  let rate1 = selected_item_1.value;
  selected_item_1.value = selected_item_2.value;
  selected_item_2.value = rate1;
  select_changed();
}

function input_1_handler(e){
  if(e.key === 'Enter'){
      e.preventDefault();
      convert_btn_clicked();
  }
}

// fetching Rates
async function getLatestData(rate1, rate2){
  query(`rates?base=${rate1}`, 'get', null, async function(response){
      if (response[0] === 200){
          rates = await response[1]['rates'];
          getFluctuations(selected_item_1.value);
          populateSelects(rates, rate1, rate2);
          setCurrentRates(convert(1, rate1, rate2), rate1, rate2);
          if (!window.sessionStorage.getItem('page')){
              setConvertedValue(convert(1, rate1, rate2), rate1, rate2);
              window.sessionStorage.setItem('page', '1');
          }
      }
  });
}

// fetching Fluctuations
async function getFluctuations(base){
  query(`fluctuations?base=${base}`, 'get', null, async function(response){
      if (response[0] === 200){
          fluctuations = await response[1]['rates'];
          displayFluctuations(selected_item_2.value);
      }
  });
};

// function to display Update in Rates 
async function displayFluctuations(base){
  console.log('displayFluctuations: ', fluctuations[base], ' ', rates[base]);
  let calc_rate = (fluctuations[base]).toFixed(3) - (rates[base]).toFixed(3);
  console.log(calc_rate);
  if (calc_rate === 0){
      console.log("hm");
      fluctns_txt_wrpr.style.visibility = 'hidden';
      return;
  }
  if (calc_rate !== 0){
      console.log("Nope");
      if (calc_rate < 0){
          fluctns_txt_wrpr.style.backgroundColor = 'rgba(140, 235, 170, 1.0)';
          fluctns_txt_wrpr.style.borderColor = 'rgba(100, 200, 110, 1.0)';
          fluctns_txt.textContent = (calc_rate).toFixed(3);
      }
      else{
          fluctns_txt_wrpr.style.backgroundColor = 'rgba(235, 145, 170, 1.0)';
          fluctns_txt_wrpr.style.borderColor = 'rgba(200, 100, 110, 1.0)';
          fluctns_txt.textContent = '+' + (calc_rate).toFixed(3);
      }
      fluctns_txt_wrpr.style.visibility = 'visible';
  }
  else{
      fluctns_txt_wrpr.style.visibility = 'hidden';
  }
}

// function to populate Both Country Select Inputs
function populateSelects(data, rate1, rate2){
  let select_options = '';
  for (const key in data){
      if (key !== rate1){
          select_options += `<option value="${key}">${key}</options>`;
      }
      else{
          select_options += `<option value="${key}" selected>${key}</options>`;
      }
  }
  selected_item_1.innerHTML = select_options;
  for (const key in data){
      if (key !== rate2){
          select_options += `<option value="${key}">${key}</options>`;
      }
      else{
          select_options += `<option value="${key}" selected>${key}</options>`;
      }
  }
  selected_item_2.innerHTML = select_options;
}

// function to convert currency using current rates
function convert(value, rate1, rate2){
  let calc = ((value/rates[rate1]) * rates[rate2]).toFixed(3);
  return calc;
}

function setCurrentRates(calc, rate1, rate2){
  rate_country_txt.textContent = rate1 + " ";
  curr_rate_text.textContent = calc + " " + rate2;
}

function setConvertedValue(calc){
  value_provided_2.value = calc;
}

// function to make various Requests to Server
function query(route, method, body, response){
  if (method === 'get'){
      fetch(route, {
          method: method,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
      }).then(async function(res){
          if (res.status === 200){
              return [res.status, await res.json()];
          }
          else{
              return [res.status, await res.json()];
          }
      }).then(function(data){
          if (data[0] === 200){
              console.log(route, " query:" ,data);
              response(data);
          }
          else{
              response(data);
          }
      }).catch(function(err) {
          console.log(err);
          alert('Error');
      });
  }
  else {
      fetch(route, {
          method: method,
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(body)
      }).then(async function(res) {
          console.log(res);
          if (res.status === 200){
              return [res.status, await res.json()];
          }
          else{
              return [res.status, await res.json()];
          }
      }).then(function(data) {
          if (data[0] === 200){
              console.log(route, " query:" ,data);
              response(data);
          }
          else{
              response(data);
          }
      }).catch(function(err) {
          console.log(err);
          alert('Error');
      });
  }
}