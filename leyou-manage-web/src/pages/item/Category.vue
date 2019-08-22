<template>
  <v-card>
      <v-flex xs12 sm10>
        <v-tree url="/item/category/list"
                :isEdit="isEdit"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
  </v-card>
</template>

<script>
  import {treeData} from '../../mockDB'
  export default {
    name: "category",
    data() {
      return {
        value1: [],
        treeData: treeData,
        isEdit:true
      }
    },
    created(){

    },
    methods: {

      handleAdd(node) {
        if(this.isEdit){
          this.handleEdit(node.id,node.name)
        }else {
          this.$http.post("/item/category/add",this.$qs.stringify(node)).then(res => {
            if(res.data){
              console.log('新增成功')
              this.reloadData(node.parentId)
            }
          }).catch(err=>{
            console.log(err)
          })
        }


      },
      handleEdit(id, name) {
        console.log("edit... id: " + id + ", name: " + name)
      },
      handleDelete(id) {
        console.log("delete ... " + id)
      },
      handleClick(node) {
        if(node.id>0){
          this.isEdit = true
        } else {
          this.isEdit = false
        }
        console.log('是不是编辑='+this.isEdit)
      },
      reloadData(id){
        //操作完成后刷新数据
        this.$http.get("/item/category/list?pid="+id).then().catch();
      }
    }
  };
</script>

<style scoped>

</style>
