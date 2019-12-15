<template>
    <div>
        <b-form-input v-model="title" placeholder="제목" />
        <b-form-textarea v-model="content" placeholder="내용" />
        <button v-on:click="write">작성</button>
    </div>
</template>
<script>
export default {
    name : 'WriteBoard',
    data() {
        return {
            title:'',
            content:''
        }
    },
    methods: {
        write() {
            
            this.$http.post('http://localhost:8080/insertBoard',{
			    title:this.title,
                content:this.content
		    }).then((response) => {
                if(response.data === 'OK'){
                    console.log('success')
                    this.$router.push({
                        path:'list'
                    })
                } else {
                    console.log('failed')
                }
		    })


        }
    }
}
</script>