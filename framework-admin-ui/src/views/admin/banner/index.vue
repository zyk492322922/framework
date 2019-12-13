<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="广告位" prop="bannerType">
        <el-select v-model="queryParams.bannerType" placeholder="请选择广告位" clearable size="small">
          <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
        </el-select>
      </el-form-item>
        <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择启用禁用" clearable size="small">
           <el-option
               v-for="dict in statusOptions"
               :key="dict.dictValue"
               :label="dict.dictLabel"
               :value="dict.dictValue"
             />
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间" prop="beginTime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="queryParams.beginTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择发布开始时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="" prop="endTime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="queryParams.endTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择发布结束时间">
              </el-date-picker>
            </el-form-item>

        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:banner:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:banner:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:banner:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['admin:banner:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="bannerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="广告名称" align="center" prop="bannerName" />
      <el-table-column label="广告位" align="center" prop="bannerType" />
      <el-table-column label="广告图片" align="center" prop="url" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="发布时间" align="center" prop="createTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:banner:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:banner:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改广告banner信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="广告位">
          <el-select v-model="form.bannerType" placeholder="请选择广告位">

          </el-select>
        </el-form-item>
        <el-form-item label="广告名称" prop="bannerName">
          <el-input v-model="form.bannerName" placeholder="请输入广告名称" />
        </el-form-item>
        <el-form-item label="广告图片" prop="url">
          <el-input v-model="form.url" placeholder="请输入广告图片" />
        </el-form-item>
        <el-form-item label="是否可跳转">
          <el-radio-group v-model="form.status">

          </el-radio-group>
        </el-form-item>
        <el-form-item label="跳转链接" prop="jumpUrl">
          <el-input v-model="form.jumpUrl" placeholder="请输入跳转链接" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="广告描述" prop="details">
          <el-input v-model="form.details" placeholder="请输入广告描述" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.type">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBanner, getBanner, delBanner, addBanner, updateBanner, exportBanner } from "@/api/admin/banner";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 广告banner信息表格数据
      bannerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bannerType: undefined,
        bannerName: undefined,
        url: undefined,
        status: undefined,
        jumpUrl: undefined,
        sort: undefined,
        details: undefined,
        createUser: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentName: [
          { required: true, message: "广告位", trigger: "blur" }
        ],        studentName: [
          { required: true, message: "是否可跳转", trigger: "blur" }
        ]   }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_user_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_user_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_user_status").then(response => {
      this.statusOptions = response.data;
    });

  },
  methods: {
    /** 查询广告banner信息列表 */
    getList() {
      this.loading = true;
      listBanner(this.queryParams).then(response => {
        this.bannerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        bannerType: undefined,
        bannerName: undefined,
        url: undefined,
        status: "0",
        jumpUrl: undefined,
        sort: undefined,
        details: undefined,
        delFlag: undefined,
        createUser: undefined,
        createTime: undefined,
        updateTime: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加广告banner信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBanner(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改广告banner信息";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateBanner(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addBanner(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除广告banner信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBanner(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有广告banner信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBanner(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
