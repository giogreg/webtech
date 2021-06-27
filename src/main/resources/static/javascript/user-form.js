const userApp = Vue.createApp({
    template: `
        <div class="row rounded-3 p-3 mt-5 mx-5 bg-white text-dark">
            <div class="col">
                <input type="text" class="form-control" v-model="longUrl" placeholder="Shorten your Link" ref="urlInput" @keyup.enter="create()">
            </div>
            <div class="col-auto">
                <a role="button" class="btn btn-info btn-width-110" @click="createUrl()">Create</a>
            </div>
        </div>
        <div class="row rounded-3 p-3 mt-4 mx-5 bg-white text-dark" v-for="(url, index) in urls" v-bind:key="index">
            <div class="col">
                <input type="text" class="form-control bg-white" v-model="url.shortUrl" readonly>
            </div>
            <div class="col-auto btn-group">
                <a role="button" class="btn btn-warning btn-width-95" v-on:click="showQrCode(index)">{{url.buttonName}}</a>
                <a role="button" class="btn btn-warning btn-width-95" v-on:click="copy(url.shortUrl)">Copy</a>
                <a role="button" class="btn btn-warning close" aria-label="Close" v-on:click="invalid(index)"><span aria-hidden="true">&times;</span></a>
            </div>
            <div v-if="url.show" class="img-con mt-3">
                <img v-bind:src="url.qrSrc" alt="" width="150" height="150">
            </div>
        </div>
    `,
    data() {
        return {
            user: '',
            longUrl: '',
            urls: [],
            qrCodes: [],
        };
    },
    methods: {
        setUser(callback) {
            axios.get('/users').then(resp => {
                    console.log(resp.data.attributes.sub);
                    this.user = resp.data.attributes.sub;
                    callback()
            })
        },
        loadUrls() {
            axios.get('/urls/' + this.user)
                .then((response) => {
                    this.urls = response.data;
                    this.addQrCode(this.urls);
                    console.log(response.data);
                }, (error) => {
                    console.log(error);
                })
        },
        addQrCode(urls) {
            for (let url of urls) {
                url.buttonName = 'QR-Code';
                url.show = false;
                //url.qrSrc = this.createQrCode(url.shortUrl);
            }
        },
        createQrCode(index) {
            //create GET request for API
            const options = {
                method: 'GET',
                url: 'https://codzz-qr-cods.p.rapidapi.com/getQrcode',
                params: {value: this.urls[index].shortUrl, type: 'url'},
                headers: {
                    'x-rapidapi-key': '4a97de30e4msh175716723bce70dp1dc769jsnf919f9025b27',
                    'x-rapidapi-host': 'codzz-qr-cods.p.rapidapi.com'
                }
            };
            //use axios to call GET request API
            axios.request(options)
                .then((response) => {
                    this.urls[index].qrSrc = response.data.url;
                    //console.log(qrSrc);
                }, (error) => {
                    console.error(error);
                })
        },
        createUrl() {
            axios.post('/urls', {
                longUrl: this.longUrl,
                userHash: this.user
            })
                .then((response) => {
                    this.longUrl = '';
                    this.urls.unshift(response.data);
                    this.urls[0].buttonName = 'QR-Code';
                    this.urls[0].show = false;
                    //this.urls[0].qrSrc = this.createQrCode(this.urls[0].shortUrl);
                }, (error) => {
                    console.log('No valid url');
                    this.longUrl = 'No valid Link - please try again'
                })
        },
        copy(shortUrl) {
            navigator.clipboard.writeText(shortUrl);
        },
        invalid(index) {
            axios.delete('/urls/' + this.urls[index].id)
            if (index > -1) {
                this.urls.splice(index, 1);
            }
        },
        showQrCode(index){
            if (this.urls[index].show) {
                this.urls[index].buttonName = 'QR-Code';
                this.urls[index].show = false;
            } else {
                this.createQrCode(index);
                console.log(this.urls[index].qrSrc)
                this.urls[index].buttonName = 'Close';
                this.urls[index].show = true;
            }
        },
    },
    mounted: function() {
        this.setUser(() => {
            this.loadUrls()
        })
    }
});

userApp.mount('#userApp');