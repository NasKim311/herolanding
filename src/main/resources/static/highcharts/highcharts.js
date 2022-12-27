let member00 = $("#member0").val();
let member01 = $("#member1").val();
let member02 = $("#member2").val();
let member03 = $("#member3").val();
let member04 = $("#member4").val();
let member05 = $("#member5").val();
let member06 = $("#member6").val();

let member0 = parseInt(member00);
let member1 = parseInt(member01);
let member2 = parseInt(member02);
let member3 = parseInt(member03);
let member4 = parseInt(member04);
let member5 = parseInt(member05);
let member6 = parseInt(member06);

let arr = new Array();

arr.push(member0);
arr.push(member1);
arr.push(member2);
arr.push(member3);
arr.push(member4);
arr.push(member5);
arr.push(member6);

let charts02;

$(function () {
  let options02 = {
    chart: {
      renderTo: "container",
      type: "spline",
    },
    title: {
      text: "회원 가입 현황",
    },
    subtitle: {
      
    },
    xAxis: {
      categories: [7, 6, 5, 4, 3, 2, 1],
      accessibility: {
        description: "최근 7일 가입자",
      },
    },
    yAxis: {
      title: {
        text: "",
      },
      labels: {
        formatter: function () {
          return this.value;
        },
      },
    },
    tooltip: {
      crosshairs: true,
      shared: true,
    },
    plotOptions: {
      spline: {
        marker: {
          radius: 4,
          lineColor: "#666666",
          lineWidth: 1,
        },
      },
    },
    series: [
      {
        name: "가입 회원 수",
        marker: {
          symbol: "square",
        },
        data: [],
      }
    ],
  };

  let data02 = [];
  data02[0] = member0;
  data02[1] = member1;
  data02[2] = member2;
  data02[3] = member3;
  data02[4] = member4;
  data02[5] = member5;
  data02[6] = member6;

  console.log(options02.series[0].data);
  options02.series[0].data = data02;
  console.log(options02.series[0].data);

  charts02 = new Highcharts.Chart(options02);
});
