import {
    m as p,
    _ as k,
    o as e,
    c as t,
    a as i,
    F as h,
    r as v,
    t as m,
    w as r,
    v as u,
    b as d,
    U as B
} from "./index-6fz5-Usj.js";

const y = {
        data() {
            return {values: {}}
        }, created() {
            let o = this.$route.query.id;
            o && this.$store.dispatch("loadBookById", o)
        }, computed: {
            ...p({book: "book"}), headers() {
                return this.$store.getters.getBooks.length ? Object.keys(this.$store.getters.getBooks[0]) : []
            }
        }, watch: {
            book: function (o, n) {
                this.values = o
            }
        }, methods: {
            createBook() {
                this.$store.dispatch("saveBook", this.values)
            }, updateBook() {
                this.$store.dispatch("updateBook", this.values)
            }
        }
    }, b = {key: 0}, f = {class: "content-section"}, g = {class: "input-line"}, x = {class: "input-line__label"},
    V = ["onUpdate:modelValue"], C = ["onUpdate:modelValue"], U = {class: "input-buttons"}, w = {key: 1},
    $ = i("section", {class: "content-section"}, [i("p", null, "Не удалось прочитать поля")], -1), j = [$];

function D(o, n, _, N, a, l) {
    return Object.keys(l.headers).length !== 0 ? (e(), t("div", b, [i("section", f, [(e(!0), t(h, null, v(l.headers, s => (e(), t("div", g, [i("label", x, m(s), 1), s == "id" ? r((e(), t("input", {
        key: 0,
        type: "text",
        "onUpdate:modelValue": c => a.values[s] = c,
        disabled: ""
    }, null, 8, V)), [[u, a.values[s]]]) : r((e(), t("input", {
        key: 1,
        type: "text",
        "onUpdate:modelValue": c => a.values[s] = c
    }, null, 8, C)), [[u, a.values[s]]])]))), 256)), i("div", U, [a.values.id ? (e(), t("button", {
        key: 0,
        onClick: n[0] || (n[0] = s => l.updateBook())
    }, "Обновить")) : (e(), t("button", {
        key: 1,
        onClick: n[1] || (n[1] = s => l.createBook())
    }, "Создать"))])])])) : (e(), t("div", w, j))
}

const F = k(y, [["render", D]]), S = {
    __name: "CreateView", setup(o) {
        return (n, _) => (e(), t("main", null, [d(B), d(F)]))
    }
};
export {S as default};
