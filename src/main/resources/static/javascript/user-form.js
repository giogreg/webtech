const userApp = Vue.createApp({
    template: `
        <div class="row rounded-3 p-3 mt-5 mx-5 bg-white text-dark">
            <div class="col">
                <input type="text" class="form-control" v-model="longUrl" placeholder="Shorten your Link" ref="urlInput" @keyup.enter="create()">
            </div>
            <div class="col-auto">
                <a role="button" class="btn btn-outline-info" @click="create()">Create</a>
            </div>
        </div>
        <div class="row rounded-3 p-3 mt-4 mx-5 bg-white text-dark" v-for="(url, index) in urls" v-bind:key="index">
            <div class="col">
                <input type="text" class="form-control bg-white" v-model="url.shortUrl" readonly>
            </div>
            <div class="col-auto">
                <a role="button" class="btn-space btn btn-outline-info" v-on:click="downloadQrCode(url.shortUrl)">QR-Code</a>
                <a role="button" class="btn-space btn btn-outline-warning" v-on:click="copy(url.shortUrl)">Copy</a>
                <a role="button" class="btn-space btn btn-outline-dark close" aria-label="Close" v-on:click="invalid(index)"><span aria-hidden="true">&times;</span></a>
            </div>
        </div>
    `,
    data() {
        return {
            longUrl: '',
            urls: [],
            user: '',
        };
    },
    methods: {
        setUser() {
            axios.get('/users').then(resp => {
                let userHash;
                if (resp.data !== '') {
                    console.log(resp.data.attributes.sub);
                    this.user = resp.data.attributes.sub;
                } else {
                    console.log("no user");
                }
            })
        },
        loadUrls() {
            axios.get('/urls/' + $cookies.get("userHash"))
                .then((response) => {
                    this.urls = response.data;
                    console.log(response.data)
                }, (error) => {
                    console.log('No valid url');
                    this.longUrl = 'No valid Link - please try again';
                })
        },
        create() {
            axios.post('/urls', {
                longUrl: this.longUrl,
                userHash: this.user
            })
                .then((response) => {
                    this.longUrl = '';
                    this.urls.unshift(response.data);
                }, (error) => {
                    console.log('No valid url');
                    this.longUrl = 'No valid Link - please try again'
                })
        },
        invalid(index) {
            axios.delete('/urls/' + this.urls[index].id)
            if (index > -1) {
                this.urls.splice(index, 1);
            }
        },
        downloadQrCode(shortUrl) {
            //create GET request for API
            let qrCodeImg = '';

            const options = {
                method: 'GET',
                url: 'https://codzz-qr-cods.p.rapidapi.com/getQrcode',
                params: {value: shortUrl, type: 'url'},
                headers: {
                    'x-rapidapi-key': '4a97de30e4msh175716723bce70dp1dc769jsnf919f9025b27',
                    'x-rapidapi-host': 'codzz-qr-cods.p.rapidapi.com'
                }
            };
            //use axios to call GET request API
            axios.request(options)
                .then(function (response) {
                    qrCodeImg = response.data.url
                    console.log(qrCodeImg);
                })
                .catch(function (error) {
                    console.error(error);
                });
            },
    },
    mounted: function() {
        this.setUser();
        this.loadUrls();
    }
});

userApp.mount('#userApp');