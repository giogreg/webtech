const app = Vue.createApp({
    template: `
        <div>
            <input v-model="longUrl" placeholder="Url" ref="urlInput" @keyup.enter="create()">
            <button type="button" @click="create()">Create</button>
        </div>
        <div v-if="show">
            <h2>Shortink</h2>
            <input v-model="shortUrl">
            <button type="button" @click="copy()">Copy</button>
            <p>Der Link ist bis zum {{validDate}} gü<!--\t&uuml;-->ltig. Um unbegrenzte Links zu erstellen, registrieren Sie sich bitte.</p>
        </div>
    `,
    data() {
        return {
            show: false,
            longUrl: '',
            shortUrl: '',
            validDate: '',
        };
    },
    methods: {
        create() {
            axios.post('/eurls', {
                longUrl: this.longUrl
            })
                .then((response) =>{
                    this.longUrl = '';
                    this.$refs.urlInput.focus();
                    this.show = true;
                    this.shortUrl = 'https://shortink.herokuapp.com/' + response.data.shortUrl;

                    let date = new Date(response.data.gueltigBis);
                    const options = { year: '2-digit', month: '2-digit', day: '2-digit' };
                    this.validDate = date.toLocaleDateString('de-DE', options);
                }, (error) => {
                    console.log('No correct url');
                });
        },
        copy() {
            navigator.clipboard.writeText(this.shortUrl);
        }
    }
});

app.mount('#app');