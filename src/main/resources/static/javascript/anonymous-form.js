const anonymousApp = Vue.createApp({
    template: `
        <div class="row rounded-3 p-3 mt-5 mx-5 bg-white text-dark">
            <div class="col">
                <input type="text" class="form-control" v-model="longUrl" placeholder="Shorten your Link" ref="urlInput" @keyup.enter="create()">
            </div>
            <div class="col-auto">
                <a role="button" class="btn btn-info btn-width-110" @click="create()">Create</a>
            </div>
        </div>
        <div v-if="show">
            <div class="row rounded-3 p-3 mt-4 mx-5 bg-white text-dark">
                <div class="col">
                    <input type="text" class="form-control bg-white" v-model="shortUrl" readonly>
                </div>
                <div class="col-auto">
                    <a role="button" class="btn btn-warning btn-width-110" ref="urlCopy" @click="copy(shortUrl)">Copy</a>
                </div>
            </div>
            <p class="info">Der Link ist bis zum {{validDate}} gültig. Um unbegrenzte Links zu erstellen, registrieren Sie sich bitte.</p>
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
                longUrl: this.longUrl,
                userHash: "anonymous",
            })
                .then((response) =>{
                    this.longUrl = '';
                    this.shortUrl = response.data.shortUrl;
                    this.show = true;
                    let date = new Date(response.data.gueltigBis);
                    const options = { year: '2-digit', month: '2-digit', day: '2-digit' };
                    this.validDate = date.toLocaleDateString('de-DE', options);

                }, (error) => {
                    console.log('No valid url');
                    this.longUrl = 'No valid Link - please try again';
                })
        },
        copy(shortUrl) {
            navigator.clipboard.writeText(shortUrl);
        }
    }
});

anonymousApp.mount('#anonymousApp');