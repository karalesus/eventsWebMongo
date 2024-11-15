<template>
    <div v-if="Object.keys(headers).length !== 0">
        <section class="content-section">
            <div v-for="header in headers" class="input-line">
                <label class="input-line__label">{{ header }}</label>
                <input v-if="header == 'id'" type="text" v-model="values[header]" disabled>
                <input v-else type="text" v-model="values[header]">
            </div>
            <div class="input-buttons">
                <button v-if="values['id']" v-on:click="updateBook()">Обновить</button>
                <button v-else v-on:click="createBook()">Создать</button>
            </div>
        </section>
    </div>
    <div v-else>
        <section class="content-section">
            <p>Не удалось прочитать поля</p>
        </section>
    </div>
</template>

<script>
    import { mapState } from 'vuex';

    export default {
        data() {
            return {
                values: {}
            }
        },
        created() {
            let id = this.$route.query.id;
            if (id) {
                this.$store.dispatch('loadBookById', id);
            }
        },
        computed: {
            ...mapState({
                book: 'book'
            }),
            headers() {
                if (this.$store.getters.getBooks.length) {
                    return Object.keys(this.$store.getters.getBooks[0]);
                } else {
                    return [];
                }
            },
        },
        watch: {
            book: function (newBook, oldBook) {
                this.values = newBook;
            }
        },
        methods: {
            createBook() {
                this.$store.dispatch('saveBook', this.values);
            },
            updateBook() {
                this.$store.dispatch('updateBook', this.values);
            }
        }
    };
</script>

<style>
    .input-line__button {
        max-width: 40px;
    }
    .input-buttons {
        text-align: center;
    }
</style>