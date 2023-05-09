<template>
  <div>
    <div class="center">
      <h3>学生信息展示页面</h3>
    </div>
    <div class="center">
      <table>
        <thead>
        <tr>
          <th>学生编号</th>
          <th>学生姓名</th>
          <th>学生性别</th>
          <th>学生年龄</th>
          <th>学生操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="student in sortedStudents" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.name }}</td>
          <td>{{ student.gender }}</td>
          <td>{{ student.age }}</td>
          <td>
            <div class="button-group">
              <button @click="removeStudent(student.id)">删除</button>
              <button @click="modifyStudent(student.id)">修改</button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <br>
    <div class="center">
      <button @click="addStudent">添加学生信息</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

axios.defaults.withCredentials = true;

export default {
  data() {
    return {
      students: [],
      maxId: 0
    };
  },
  computed: {
    sortedStudents: function() {
      const sorted = [...this.students]; // 创建students的副本
      sorted.sort(function(a, b) {
        return a.id - b.id;
      });
      return sorted;
    }
  },
  methods: {
    queryStudents() {
      axios.get("http://localhost:8081/student/queryAll").then(response => {
        console.log(response.data);
        this.students = response.data.map(student => {
          student.id = parseInt(student.id);
          return student;
        });
        this.students.forEach(student => {
          if (student.id > this.maxId) {
            this.maxId = student.id;
          }
        });
      });
    },
    removeStudent(id) {
      axios.delete('http://localhost:8081/student/removeById', {
        data: { id }
      }).then(() => {
        this.queryStudents();
      });
      this.maxId--;
    },
    modifyStudent(id) {
      // navigate to the modify student page
      console.log("跳到修改学生页面");
      this.$router.push({
        path: "/modifyStudent",
        query: { id: id }
      });
    },
    addStudent() {
      // navigate to the add student page
      console.log("跳到增加学生页面");
      this.$router.push({
        path: "/addStudent",
        query: { id: this.maxId + 1}
      });
      this.maxId++;
    }
  },
  mounted() {
    this.queryStudents();
  }
};
</script>

<style>
/* 居中元素 */
.center {
  display: flex;
  justify-content: center;
  align-items: center;
}
table {
  border: 4px double #39C5BB;
  width: 600px;
  border-collapse: collapse;
}
td {
  border: 1px solid black;
}
.button-group {
  display: flex;
  justify-content: space-between;
  width: 100%; /* 固定宽度，根据实际需要调整 */
}

</style>