<template>
  <div id="bg">
    <el-row :gutter="8" style="margin-bottom: 60px">
      <el-col :span="5">
        <el-card style="color: #E6A23C">
          <div><i class="el-icon-user-solid" /> 新增确诊</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ this.addAsymptomatic }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-money" /> 新增无症状</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ this.addConfirmedCases }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 新增治愈</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ this.addCured }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-s-shop" /> 新增死亡</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
            {{ this.addDeaths }}
          </div>
        </el-card>
      </el-col>
      <el-date-picker
          style="margin-top: 50px; margin-left: 5px"
          v-model="value1"
          type="datetime"
          placeholder="选择日期时间"
          value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-button class="ml-5" type="primary" @click="load" style="margin-left: 160px; margin-top: 20px">搜索</el-button>

    </el-row>

    <el-row >
      <el-col :span="7" style="margin-left: 50px">
        <div id="main1" style="width: 480px; height: 330px"></div>
      </el-col>

      <el-col :span="7" style="margin-left: 50px">
        <div id="main2" style="width: 480px; height: 330px"></div>
      </el-col>

      <el-col :span="7" style="margin-left: 50px">
        <div id="main3" style="width: 480px; height: 330px"></div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="7" style="margin-left: 50px">
        <div id="main4" style="width: 480px; height: 330px"></div>
      </el-col>

      <el-col :span="7" style="margin-left: 50px">
        <div id="main5" style="width: 480px; height: 330px"></div>
      </el-col>

      <el-col :span="7" style="margin-left: 50px">
        <div id="main6" style="width: 450px; height: 330px"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import request from "@/utils/request";

export default {
  name: "EpDisplayBoard",
  data() {
    return {
      value1: '',
      time: "2022.08.31",
      addAsymptomatic: 0,
      addConfirmedCases: 0,
      addCured: 0,
      addDeaths: 0,
      addAsymptomaticInLastYear: 0,
      addConfirmedCasesInLastYear: 0,
      addCuredInLastYear: 0,
      addDeathsInLastYear: 0,
    }
  },
  methods: {
    load() {
      this.mounted1()
    },
    mounted1() {
      var chartDom1 = document.getElementById('main1');
      var myChart1 = echarts.init(chartDom1);
      var option1;
      option1 = {
        title: {
          text: '新增无症状人数',
          x:'center',
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      };
      option1 && myChart1.setOption(option1);

      var chartDom2 = document.getElementById('main2');
      var myChart2 = echarts.init(chartDom2);
      var option2;

      option2 = {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option2 && myChart2.setOption(option2);

      var chartDom3 = document.getElementById('main3');
      var myChart3 = echarts.init(chartDom3);
      var option3;
      option3 = {
        title: {
          text: '新增确诊人数',
          x:'center',
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      };
      option3 && myChart3.setOption(option3);


      var chartDom4 = document.getElementById('main4');
      var myChart4 = echarts.init(chartDom4);
      var option4;
      option4 = {
        title: {
          text: '新增治愈人数',
          x:'center',
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      };
      option4 && myChart4.setOption(option4);

      var chartDom5 = document.getElementById('main5');
      var myChart5 = echarts.init(chartDom5);
      var option5;
      option5 = {
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'bar'
          },
          {
            data: [],
            type: 'bar'
          }
        ]
      };
      option5 && myChart5.setOption(option5);

      var chartDom6 = document.getElementById('main6');
      var myChart6 = echarts.init(chartDom6);
      var option6;
      option6 = {
        title: {
          text: '新增死亡人数',
          x:'center',
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      };
      option6 && myChart6.setOption(option6);
      if (this.value1 === "") {
        this.time = "2022.08.31"
      } else {
        this.time = this.value1
      }

      request.get("/ep/selectAddEpByTime", {
        params: {
          time: this.time,
        }
      }).then(res => {
        console.log(res)
        var arrInX = []
        for (var i = 0; i < res.length; ++i) {
          arrInX.push(res[i].deadline)
        }
        var arrInY = []
        for (var i = 0; i < res.length; ++i) {
          arrInY.push(res[i].addAsymptomatic)
        }
        option1.xAxis.data = arrInX
        option1.series[0].data = arrInY
        myChart1.setOption(option1)

        arrInY = []
        for (var i = 0; i < res.length; ++i) {
          arrInY.push(res[i].addConfirmedCases)
        }
        option3.xAxis.data = arrInX
        option3.series[0].data = arrInY
        myChart3.setOption(option3)

        arrInY = []
        for (var i = 0; i < res.length; ++i) {
          arrInY.push(res[i].addCured)
        }
        option4.xAxis.data = arrInX
        option4.series[0].data = arrInY
        myChart4.setOption(option4)

        arrInY = []
        for (var i = 0; i < res.length; ++i) {
          arrInY.push(res[i].addDeaths)
        }
        option6.xAxis.data = arrInX
        option6.series[0].data = arrInY
        myChart6.setOption(option6)
      })
      this.request.get("/ep/selectAddEpRaitoByTime", {
        params: {
          time: this.time,
        }
      }).then(res => {

        // 填空
        // option.xAxis.data = res.data.x
        console.log(res)
        this.addAsymptomatic = res.addAsymptomatic
        this.addConfirmedCases = res.addConfirmedCases
        this.addCured = res.addCured
        this.addDeaths = res.addDeaths

        // 数据准备完毕之后再set
        option2.series[0].data = [
          {name: "无症状", value: parseFloat(res.asymptomaticRatio)},
          {name: "治愈", value: parseFloat(res.curedRatio)},
          {name: "死亡", value: parseFloat(res.deathsRatio)},
        ]
        myChart2.setOption(option2)
      })
      // 直方图
      this.request.get("/ep/selectEpInLastYear", {
        params: {
          time: this.time,
        }
      }).then(res => {
        // 填空
        // option.xAxis.data = res.data.x
        console.log(res)
        this.addAsymptomaticInLastYear = res.addAsymptomatic
        this.addConfirmedCasesInLastYear = res.addConfirmedCases
        this.addCuredInLastYear = res.addCured
        this.addDeathsInLastYear = res.addDeaths
        option5.xAxis.data = ['无症状人数', '确诊人数', '治愈人数', '死亡人数']
        option5.series[0].data = [this.addAsymptomatic, this.addConfirmedCases, this.addCured, this.addDeaths]
        option5.series[1].data = [this.addAsymptomaticInLastYear, this.addConfirmedCasesInLastYear, this.addCuredInLastYear, this.addDeathsInLastYear]
        // 数据准备完毕之后再set
        myChart5.setOption(option5)
      })
    }
  },
  mounted() {
    var chartDom1 = document.getElementById('main1');
    var myChart1 = echarts.init(chartDom1);
    var option1;
    option1 = {
      title: {
        text: '新增无症状人数',
        x:'center',
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        }
      ]
    };
    option1 && myChart1.setOption(option1);

    var chartDom2 = document.getElementById('main2');
    var myChart2 = echarts.init(chartDom2);
    var option2;

    option2 = {
      title: {
        text: 'Referer of a Website',
        subtext: 'Fake Data',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            // { value: 1048, name: 'Search Engine' },
            // { value: 735, name: 'Direct' },
            // { value: 580, name: 'Email' },
            // { value: 484, name: 'Union Ads' },
            // { value: 300, name: 'Video Ads' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

    option2 && myChart2.setOption(option2);

    var chartDom3 = document.getElementById('main3');
    var myChart3 = echarts.init(chartDom3);
    var option3;
    option3 = {
      title: {
        text: '新增确诊人数',
        x:'center',
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        }
      ]
    };
    option3 && myChart3.setOption(option3);


    var chartDom4 = document.getElementById('main4');
    var myChart4 = echarts.init(chartDom4);
    var option4;
    option4 = {
      title: {
        text: '新增治愈人数',
        x:'center',
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        }
      ]
    };
    option4 && myChart4.setOption(option4);

    var chartDom5 = document.getElementById('main5');
    var myChart5 = echarts.init(chartDom5);
    var option5;
    option5 = {
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'bar'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
    option5 && myChart5.setOption(option5);

    var chartDom6 = document.getElementById('main6');
    var myChart6 = echarts.init(chartDom6);
    var option6;
    option6 = {
      title: {
        text: '新增死亡人数',
        x:'center',
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        }
      ]
    };
    option6 && myChart6.setOption(option6);
    if (this.value1 === "") {
      this.time = "2022.08.31"
    } else {
      this.time = this.value1
    }
    request.get("/ep/selectAddEpByTime", {
      params: {
        time: this.time,
      }
    }).then(res => {
      console.log(res)
      var arrInX = []
      for (var i = 0; i < res.length; ++i) {
        arrInX.push(res[i].deadline)
      }
      var arrInY = []
      for (var i = 0; i < res.length; ++i) {
        arrInY.push(res[i].addAsymptomatic)
      }
      option1.xAxis.data = arrInX
      option1.series[0].data = arrInY
      myChart1.setOption(option1)

      arrInY = []
      for (var i = 0; i < res.length; ++i) {
        arrInY.push(res[i].addConfirmedCases)
      }
      option3.xAxis.data = arrInX
      option3.series[0].data = arrInY
      myChart3.setOption(option3)

      arrInY = []
      for (var i = 0; i < res.length; ++i) {
        arrInY.push(res[i].addCured)
      }
      option4.xAxis.data = arrInX
      option4.series[0].data = arrInY
      myChart4.setOption(option4)

      arrInY = []
      for (var i = 0; i < res.length; ++i) {
        arrInY.push(res[i].addDeaths)
      }
      option6.xAxis.data = arrInX
      option6.series[0].data = arrInY
      myChart6.setOption(option6)
    })
    this.request.get("/ep/selectAddEpRaitoByTime", {
      params: {
        time: this.time,
      }
    }).then(res => {

      // 填空
      // option.xAxis.data = res.data.x
      console.log(res)
      this.addAsymptomatic = res.addAsymptomatic
      this.addConfirmedCases = res.addConfirmedCases
      this.addCured = res.addCured
      this.addDeaths = res.addDeaths

      // 数据准备完毕之后再set
      option2.series[0].data = [
        {name: "无症状", value: parseFloat(res.asymptomaticRatio)},
        {name: "治愈", value: parseFloat(res.curedRatio)},
        {name: "死亡", value: parseFloat(res.deathsRatio)},
      ]
      myChart2.setOption(option2)
    })
    // 直方图
    this.request.get("/ep/selectEpInLastYear", {
      params: {
        time: this.time,
      }
    }).then(res => {
      // 填空
      // option.xAxis.data = res.data.x
      console.log(res)
      this.addAsymptomaticInLastYear = res.addAsymptomatic
      this.addConfirmedCasesInLastYear = res.addConfirmedCases
      this.addCuredInLastYear = res.addCured
      this.addDeathsInLastYear = res.addDeaths
      option5.xAxis.data = ['无症状人数', '确诊人数', '治愈人数', '死亡人数']
      option5.series[0].data = [this.addAsymptomatic, this.addConfirmedCases, this.addCured, this.addDeaths]
      option5.series[1].data = [this.addAsymptomaticInLastYear, this.addConfirmedCasesInLastYear, this.addCuredInLastYear, this.addDeathsInLastYear]
      // 数据准备完毕之后再set
      myChart5.setOption(option5)
    })
  }
}
</script>


<style scoped>
#bg {
  width: 80%;
  height: 80vh;
  /*background: url("../assets/bg.jpg") center center no-repeat;*/
  background-size: 100% 100%;
  position: absolute;
}
</style>