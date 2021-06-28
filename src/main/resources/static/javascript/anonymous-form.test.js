import { mount } from '@vue/test-utils';
import anonymousForm from './anonymous-form.js';
global.axios = {
    get() { return Promise.resolve({ data: [] }) },
    post() { return Promise.resolve('value') }
}

test('create button displayed', () => {
    const wrapper = mount(anonymousForm);
    expect(wrapper.find('a[role="button"]').text()).toBe('Create');
})