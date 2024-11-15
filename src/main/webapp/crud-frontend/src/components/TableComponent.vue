<template>
    <section class="content-section">
        <div v-if="isEmpty === true">
            Список пуст
        </div>
        <div v-else>
            <table class="table">
                <tr>
                    <td v-for="header in headers" class="table__header">{{ header }}</td>
                    <td></td>
                </tr>
                <tr v-for="item in list">
                    <td v-for="field in item" class="table__item">{{ field }}</td>
                    <td>
                        <div class="table__button" v-on:click="editItem(Object.values(item)[0])">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                            </svg>
                        </div>
                        <div class="table__button" v-on:click="deleteItem(Object.values(item)[0])">
                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                            </svg>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="pagination">
                <div v-for="(n, page) in totalPages">
                    <a v-if="page === currentPage" v-on:click="getPage(page)" class="pagination__current-page">{{ page }}</a>
                    <a v-else v-on:click="getPage(page)">{{ page }}</a>
                </div>
            </div>
        </div>
        <div class="pagination-limit">
            <label for="limit">Показывать по: </label>
            <input class="pagination-limit__input" type="text" v-model="countOnPage">
            <button v-on:click="update()">Обновить</button>
        </div>
    </section>
</template>

<script>
    import { mapState } from 'vuex';

    export default {
        name: 'TableComponent',
        computed: {
            ...mapState({
                list: 'books',
                isEmpty: 'isEmpty',
                totalPages: 'totalPages',
                currentPage: 'currentPage',
            }), 
            headers() {
                if (this.$store.getters.getBooks.length) {
                    return Object.keys(this.$store.getters.getBooks[0]);
                } else {
                    return [];
                }
            },
            countOnPage: {
                get() {
                    return this.$store.getters.getCountOnPage;
                },
                set(value) {
                    this.$store.commit('setCountOnPage', value);
                }
            }
        },
        created() {
            this.$store.dispatch('loadBooks');
        },
        methods: {
            editItem(id) {
                let routeData = this.$router.push({name: 'create', query: {id: id}});
            },
            deleteItem(id) {
                this.$store.dispatch('deleteBook', id);
            },
            update() {
                this.$store.dispatch('loadBooks');
            },
            getPage(page) {
                this.$store.commit('setCurrentPage', page);
                this.update();
            }
        }
    };
</script>

<style>
    .table__header {
        font-weight: bold;
    }

    .table td {
        line-height: 1;
        padding: 0;
    }

    .table__button {
        display: inline-block;
    }

    .pagination {
        flex-wrap: wrap;
    }

    .pagination__current-page {
        text-decoration: underline;
    }

    .pagination-limit {
        text-align: center;
    }

    .pagination-limit__input {
        max-width: 30px;
        margin-left: 10px;
    }
</style>