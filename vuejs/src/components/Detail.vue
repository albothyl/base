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
                    <b-row class="my-1">
                        <b-col sm="12">
                            <b-card class="text-center" v-for="(item, index) in comments" :key="item.commentId">
                                <div v-if="item.member.memberId === 6" class="bg-secondary text-light" align="left">
                                    <b-form-textarea v-model="item.contents"/>
                                    <div>memberId:{{item.member.memberId}} 변경일시:{{moment(item.modifiedAt).format('YYYY-MM-DD hh:mm:ss')}}</div>
                                    <div>
                                        <button v-on:click="updateComment(item.commentId, item.contents)">댓글수정</button>
                                        <button v-on:click="deleteComment(item.commentId)">댓글삭제</button>
                                    </div>
                                </div>
                                <div v-else class="bg-secondary text-light"  align="left">
                                    <div>{{item.contents}}</div>
                                    <div>memberId:{{item.member.memberId}} 변경일시:{{moment(item.modifiedAt).format('YYYY-MM-DD hh:mm:ss')}}</div>
                                </div>
                            </b-card>
                            <b-form-textarea v-model="comment.contents" />
                            <button v-on:click="createComment(board.postId)">댓글생성</button>
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
            },
            comment:{
                contents:'',
            },
            comments: []
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
            this.comments = response.data.comments
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
        },
        createComment(postId) {
            this.$http.post(`/posts/comment`,{
                contents: this.comment.contents,
                postId: postId,
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
                    alert('댓글생성 성공!')
                    console.log('comment create success')
                    this.$router.go(this.$router.currentRoute)
                } else {
                    alert('댓글생성 실패!')
                    console.log('comment create failed')
                }
		    }).catch(error => {
                alert('댓글생성 실패! '+error)
                console.log(error)
            })
        },
        updateComment(commentId, contents) {
            this.$http.patch(`/posts/comment/${commentId}`,{
                contents: contents,
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
                    alert('댓글수정 성공!')
                    console.log('comment update success')
                    this.$router.go(this.$router.currentRoute)
                } else {
                    alert('댓글수정 실패!')
                    console.log('comment update failed')
                }
		    }).catch(error => {
                alert('댓글수정 실패! '+error)
                console.log(error)
            })
        },
        deleteComment(commentId) {
            this.$http.delete(`/posts/comment/${commentId}`,{
                headers: {
                    Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                }
		    }).then((response) => {
                console.log(response)
                if(response.data !== null){
                    alert('댓글삭제 성공!')
                    console.log('comment delete success')
                    this.$router.go(this.$router.currentRoute)
                } else {
                    alert('댓글삭제 실패!')
                    console.log('comment delete failed')
                }
            }).catch(error => {
                alert('댓글삭제 실패! '+error)
                console.log(error)
            })
        }
    }
}
</script>