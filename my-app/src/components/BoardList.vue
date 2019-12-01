<template>
    <div>
        <table>
            <tr>
                <td>순서</td>
                <td>제목</td>
                <td>내용</td>
                <td>등록일</td>
            </tr>
            <tr v-for="item in boards" v-bind:key="item.seq" @click="detail(item.seq)">
                <td>{{item.seq}}</td>
                <td>{{item.title}}</td>
                <td>{{item.content}}</td>
                <td>{{item.createDate}}</td>
            </tr>
        </table>
        <button v-on:click="write">글쓰기</button>
    </div>
</template>
<script>

export default {
    name: 'BoardList',
    data() {
        return {
            boards:[]
        }
    },
    created() {
        this.$http.get('http://localhost:8080/list').then((response) => {
            console.log(response)
            this.boards = response.data
        })
    },
    methods: {
        write() {
            this.$router.push({
                path:'insertBoard'
            })
        },
        detail(index) {
            this.$router.push({
                name:'Detail',
                params: {
                    seq: index
                }
            })
        }
    },
}
</script>