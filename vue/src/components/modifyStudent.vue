<template>
  <div class="form-wrapper">
    <h3>学生信息修改页面</h3>
    <form @submit.prevent="submitForm">
      <label>学生姓名:</label>
      <input type="text" v-model="name"><br>
      <input type="hidden" name="id" v-model="id">
      <div class="blank-space"></div>
      <label>学生年龄:</label>
      <input type="text" v-model="age"><br>
      <div class="blank-space"></div>
      <label>学生性别:</label>
      <input type="radio" name="gender" value="男" v-model="gender">男
      <input type="radio" name="gender" value="女" v-model="gender" style="margin-left: 50px;">女
      <div class="blank-space"></div>
      <button type="submit">修改</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      id: null,
      name: '',
      age: '',
      gender: ''
    }
  },
  mounted() {
    // 获取当前学生信息
    this.getStudentInfo();
  },
  methods: {
    // 获取当前学生信息
    getStudentInfo() {
      const url = `http://localhost:8081/student/queryById?id=${this.$route.query.id}`;
      axios.get(url).then(res => {
        const data = res.data;
        this.id = data.id;
        this.name = data.name;
        this.age = data.age;
        this.gender = data.gender;
      }).catch(err => {
        console.error(err);
      });
    },
    // 提交表单
    submitForm() {
      const url = 'http://localhost:8081/student/changeStudent';
      const data = {
        id: this.id,
        name: this.name,
        age: this.age,
        gender: this.gender
      };
      console.log(this.age);
      axios.post(url, data).then(() => {
        // 修改成功后跳转到学生列表页面
        this.$router.push('/showAll');
      }).catch(err => {
        console.error(err);
      });
    }
  }
}
</script>

<style>
.form-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(40vh); /* 100px为上方留白的高度 */
}

.blank-space {
  height: 20px; /* 定义上方留白的高度 */
}

button[type="submit"] {
  margin-left: 100px;
}
</style>

