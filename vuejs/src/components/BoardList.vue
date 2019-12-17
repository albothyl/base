<template>
    <div class="overflow-auto">
        <button v-on:click="write">글쓰기</button>
        <table class="table">
            <thead>
            <tr>
                <th>seq</th>
                <th>title</th>
                <th>content</th>
                <th>createDate</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item, index) in boards" :key="item.seq" @click="detail(item.seq)">
                <td>{{item.seq}}</td>
                <td>{{item.title}}</td>
                <td>{{item.content}}</td>
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
        aria-controls="my-table"
        ></b-pagination>

        <p class="mt-3">Current Page: {{ page }}</p>
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
            sort: 'seq,desc',
            rows: ''
        }
    },
    created() {
        this.$http.get('http://localhost:8080/boards', {
                        params: {
                            page: this.page-1,
                            size: this.size,
                            sort: this.sort
                        }
        }).then((response) => {
            console.log(response)
            this.boards = response.data.content
            this.page = response.data.number+1
            this.size = response.data.size
            this.rows = response.data.totalElements
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
        },
        changePage(requestPage) {
            this.$http.get('http://localhost:8080/boards', {
                        params: {
                            page: requestPage-1,
                            size: this.size,
                            sort: this.sort
                        }
            }).then((response) => {
                console.log(response)
                this.boards = response.data.content
                this.page = response.data.number+1
                this.size = response.data.size
                this.rows = response.data.totalElements
            })
        }
    },
}
</script>
