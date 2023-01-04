<template>
  <div>
    <div style="margin: 10px 0">
      <el-date-picker
          v-model="value2"
          type="datetimerange"
          align="right"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['12:00:00', '08:00:00']"
          value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">
        新增
        <i class="el-icon-circle-plus-outline"></i>
      </el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">
          批量删除
          <i class="el-icon-remove-outline"></i>
        </el-button>
      </el-popconfirm>
      <el-upload action="https://localhost:9000/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">
          导入
          <i class="el-icon-bottom"></i>
        </el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">
        导出
        <i class="el-icon-top"></i>
      </el-button>
      <el-select v-model="value" placeholder="请选择" @change="selectChange(value)">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            >
        </el-option>
      </el-select>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="date" label="时间" width="240">
      </el-table-column>
      <el-table-column prop="addAsymptomatic" label="无症状人数" width="100">
      </el-table-column>
      <el-table-column prop="asymptomaticRatio" label="无症状率" width="100">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit()">
            编辑
            <i class="el-icon-edit">
            </i>
          </el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del()"
          >
            <el-button type="danger" slot="reference">
              删除
              <i class="el-icon-remove-outline">
              </i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      startTime: "2020.01.31",
      endTime: "2022.08.31",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      value: '',
      value2: '',
      selectChoice: '',
      options: [{
        value: '选项1',
        label: '时间顺序'
      }, {
        value: '选项2',
        label: '无症状人数'
      }, {
        value: '选项3',
        label: '无症状率'
      }],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      console.log(this.value2[0])
      console.log(this.value2[1])
      if (this.value2 === "") {
        this.startTime = "2020.01.31"
        this.endTime = "2022.08.31"
      } else {
        if (this.value2[0] === "") {
          this.startTime = "2020.01.31"
        } else {
          this.startTime = this.value2[0]
        }
        if (this.value2[1] === "") {
          this.endTime = "2022.08.31"
        } else {
          this.endTime = this.value2[1]
        }
      }
      request.get("/ep/asymptomatic/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          startTime: this.startTime,
          endTime: this.endTime,
          selectChoice: this.selectChoice
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data
        this.total = res.size
      })
    },
    addUser() {
      request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit() {
      this.$alert('该数据不允许修改编辑', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `取消编辑`
          });
        }
      })
    },
    del() {
      this.$alert('该数据不允许删除', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `取消删除`
          });
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delBatch() {
      this.$alert('该数据不允许被批量删除', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `取消删除`
          });
        }
      })
    },
    reset() {
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    exp() {
      window.open("http://localhost:9090/user/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    },
    selectChange(val) {
      if (val === "选项1") {
        this.selectChoice = 1
      } else if (val === "选项2") {
        this.selectChoice = 2
      } else if (val === "选项3") {
        this.selectChoice = 3
      }
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee !important;
}
</style>
