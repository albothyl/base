<template>
    <div>

        <b-container fluid>
            <b-row>
                <b-col md="8" offset-md="2">
                    <b-row class="my-1">
                        <b-col sm="2">
                        <label for="input-none">순서 :</label>
                        </b-col>
                        <b-col sm="10">
                            {{board.postId}}
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                        <label for="input-none">제목 :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-input v-model="board.title"/>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="2">
                        <label for="input-none">내용 :</label>
                        </b-col>
                        <b-col sm="10">
                            <b-form-textarea v-model="board.contents"/>
                        </b-col>
                    </b-row>
                    <b-row class="my-1">
                        <b-col sm="12">
                            <button v-on:click="updateBoard(board.postId)">수정</button>
                            <button v-on:click="deleteBoard(board.postId)">삭제</button>
                        </b-col>
                    </b-row>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>
<script>
export default {
    name : 'Detail',
    data() {
        return {
            board:{
                postId:'',
                title:'',
                contents:''
            }
        }
    },
    created() {
        const postId = this.$route.params.postId
        this.$http.get(`/posts/${postId}`, {
                headers: {
                    Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                }
		}).then((response) => {
            console.log(response)
            this.board = response.data
        }).catch(error => {
            alert('글조회 실패! '+error)
            console.log(error)
        })
    },
    methods: {
        updateBoard(postId) {
            this.$http.put(`/posts/${postId}`,{
			    title: this.board.title,
                contents: this.board.contents,
                boardType: 'FREE',
                member: {
                    memberId:6,
                }
		    },
            {
                headers: {
                    Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                }
		    }).then((response) => {
                if(response.data !== null){
                    alert('수정 성공!')
                    console.log('update success')
                } else {
                    alert('수정 실패!')
                    console.log('update failed')
                }
		    }).catch(error => {
                alert('수정 실패! '+error)
                console.log(error)
            })
        },
        deleteBoard(postId) {
            this.$http.delete(`/posts/${postId}`,{
                headers: {
                    Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                }
		    }).then((response) => {
                console.log(response)
                if(response.data !== null){
                    alert('삭제 성공!')
                    console.log('delete success')
                    this.$router.push({
                        path:'/posts'
                    })
                } else {
                    alert('삭제 실패!')
                    console.log('delete failed')
                }
            }).catch(error => {
                alert('삭제 실패! '+error)
                console.log(error)
            })
        }
    }
}
</script>