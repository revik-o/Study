<template>
  <div>
    <h1>Oriane Test Bench</h1>
    <pre>Total Users: {{ countUsers }}</pre>
    <div v-for="user in parsedData" :key="user._id">
      <p>{{ user.name }}</p>
      <p>{{ user.email }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
const { data } = await useFetch('/api/v1/users')
const countUsers = computed(() => data.value?.count)
const parsedData = computed(() => data.value?.users)
</script>