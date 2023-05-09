<template>
  <div class="form-wrapper">
    <h3>学生信息添加页面</h3>
    <form @submit.prevent="addStudent">
      <label>学生姓名:    </label>
      <input type="text" v-model="name"><br>
      <div class="blank-space"></div>
      <label>学生年龄:    </label>
      <input type="text" v-model="age"><br>
      <div class="blank-space"></div>
      <label>学生性别:    </label>
      <input type="radio" v-model="gender" value="男" checked>男
      <input type="radio" v-model="gender" value="女" style="margin-left: 50px;">女
      <div class="blank-space"></div>
      <input type="submit" value="提交">
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      name: "",
      age: "",
      gender: "男"
    };
  },
  methods: {
    addStudent() {
      // send form data to server
      axios
          .post("http://localhost:8081/student/addStudent", {
            id: this.$route.query.id,
            name: this.name,
            age: this.age,
            gender: this.gender
          })
          .then(() => {
            // navigate to showAll page
            this.$router.push("/showAll");
          });
    }
  }
};
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

input[type="submit"] {
  margin-left: 100px;
}
</style>
