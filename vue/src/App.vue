<template>
  <div>
    <div v-if="$route.path === '/'">
      <h1>Welcome to Your Vue.js App</h1>
      <button @click="$router.push('/showAll')">进入学生信息展示页面</button>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
