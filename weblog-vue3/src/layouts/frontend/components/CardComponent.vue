<template>
    <div class="card">
      <div class="card-header">
        <button @click="toggleContent">
          {{ isCollapsed ? "Show More" : "Show Less" }}
        </button>
      </div>
      <div class="card-body">
        <div v-if="!isCollapsed">
          <div v-for="(tag, index) in tags" :key="index" class="tag">
            {{ tag }}
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isCollapsed: true,
        tags: [
          'Tag1', 'Tag2', 'Tag3', 'Tag4', 'Tag5', 'Tag6', 'Tag7', 'Tag8', 'Tag9', 'Tag10'
        ]
      };
    },
    methods: {
      toggleContent() {
        this.isCollapsed = !this.isCollapsed;
      }
    }
  };
  </script>
  
  <style>
  .card {
    border: 1px solid #ddd;
    padding: 16px;
    margin: 16px;
  }
  .card-header {
    display: flex;
    justify-content: flex-end;
  }
  .tag {
    margin: 4px;
    display: inline-block;
    background-color: #f1f1f1;
    padding: 4px 8px;
    border-radius: 4px;
  }
  </style>
  