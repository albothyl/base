<template>
    <div>
        <div>순서 {{board.seq}}</div>
        <div>제목 <input type="text" v-model="board.title"/></div>
        <div>내용 <input type="text" v-model="board.content"/></div>
        <div>
            <button v-on:click="updateBoard">수정</button>
            <button v-on:click="deleteBoard(board.seq)">삭제</button>
        </div>
    </div>
</template>
<script>
export default {
    name : 'Detail',
    data() {
        return {
            board:{
                seq:'',
                title:'',
                content:''
            }
        }
    },
    created() {
        const seq = this.$route.params.seq
        this.$http.get(`http://localhost:8080/getBoard/${seq}`).then((response) => {
            console.log(response)
            this.board = response.data
        })
    },
    methods: {
        updateBoard() {
            this.$http.post('http://localhost:8080/updateBoard',{
                seq: this.board.seq,
			    title: this.board.title,
                content: this.board.content
		    }).then((response) => {
                if(response.data === 'OK'){
                    console.log('success')
                } else {
                    console.log('failed')
                }
		    })
        },
        deleteBoard(seq) {
            this.$http.get(`http://localhost:8080/deleteBoard/${seq}`).then((response) => {
                console.log(response)
                if(response.data === 'OK'){
                    console.log('success')
                    this.$router.push({
                        path:'/list'
                    })
                } else {
                    console.log('failed')
                }
            })
        }
    }
}
</script>