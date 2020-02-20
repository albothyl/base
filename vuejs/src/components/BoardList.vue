<template>
    <div>
        <b-container fluid>
            <b-row>
                <b-col md="3" offset-md="8">
                    <button v-on:click="write" align="right">글쓰기</button>
                </b-col>
                <b-col md="8" offset-md="2">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>postId</th>
                            <th>title</th>
                            <th>content</th>
                            <th>createDate</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(item, index) in boards" :key="item.postId" @click="detail(item.postId)">
                            <td>{{item.postId}}</td>
                            <td>{{item.title}}</td>
                            <td>{{item.contents}}</td>
                            <td>{{moment(item.createDate).format('YYYY-MM-DD hh:mm:ss')}}</td>
                        </tr>
                        </tbody>
                    </table>

                    <!-- v-on:change="changePage()"  changePage 메소드의 인자로 제대로된 페이지값이 전될되지 않아서 사용하지 않음. @change 를 사용함 -->
                    <b-pagination
                    @change="changePage"
                    v-model="page"
                    :total-rows="rows"
                    :per-page="size"
                    align="center"
                    aria-controls="my-table"
                    ></b-pagination>

                    <!-- <p class="mt-3">Current Page: {{ page }}</p>  -->
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>
<script>

export default {
    name: 'BoardList',
    data() {
        return {
            boards: [],
            fields: ['seq', 'title', 'content', 'createDate'],
            page: 1,
            size: 5,
            sort: 'postId,desc',
            rows: ''
        }
    },
    created() {
        this.$http.get('/posts', {
                        params: {
                            page: this.page-1,
                            size: this.size,
                            sort: this.sort
                        },
                        headers: {
                            Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                        }
        }).then((response) => {
            console.log(response)
            this.boards = response.data.content
            this.page = response.data.number+1
            this.size = response.data.size
            this.rows = response.data.totalElements
        }).catch(error => {
            alert('리스트 조회 실패! '+error)
            console.log(error)
        })
    },
    methods: {
        write() {
            this.$router.push({
                path:'insertPost'
            })
        },
        detail(index) {
            this.$router.push({
                name:'Detail',
                params: {
                    postId: index
                }
            })
        },
        changePage(requestPage) {
            this.$http.get('/posts', {
                        params: {
                            page: requestPage-1,
                            size: this.size,
                            sort: this.sort
                        },
                        headers: {
                            Authorization : this.$store.getters.token?'Bearer '+this.$store.getters.token:null
                        }
            }).then((response) => {
                console.log(response)
                this.boards = response.data.content
                this.page = response.data.number+1
                this.size = response.data.size
                this.rows = response.data.totalElements
            }).catch(error => {
                alert('리스트 조회 실패! '+error)
                console.log(error)
            })
        }
    },
}
</script>
