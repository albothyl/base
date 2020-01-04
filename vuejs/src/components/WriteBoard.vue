<template>
    <div>
        <b-container fluid>
            <b-row>
                <b-col md="8" offset-md="2">
                    <b-row class="my-1">
                        <b-col sm="2">
                        <label for="input-none">제목 :</label>
                        </b-col>
                        <b-col sm="10">
                        <b-form-input v-model="title" />
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                        <label for="input-none">내용 :</label>
                        </b-col>
                        <b-col sm="10">
                        <b-form-textarea v-model="content" />
                        </b-col>
                    </b-row>
                </b-col>
            </b-row>
        </b-container>
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
            
            this.$http.post('/boards',{
			    title:this.title,
                content:this.content
		    }).then((response) => {
                if(response.data === 'OK'){
                    console.log('success')
                    this.$router.push({
                        path:'boards'
                    })
                } else {
                    console.log('failed')
                }
		    }).catch(error => {
                alert('글쓰기 실패! '+error)
                console.log(error)
            })


        }
    }
}
</script>