import { mount } from '@vue/test-utils'
import Tag from '@/components/Tag.vue'

describe('Tag.vue', () => {
  it('Name properties should be required', () => {
    const props = { name: 'Name Tags', readOnly: true }
    const wrapper = mount(Tag, { propsData: props })

    const { name } = wrapper.vm.$options.props
    expect(name.required).toBeTruthy()
    expect(name.type).toBe(String)
  })

  it('Have remover btn ', () => {
    const name = 'Name Tags'
    const wrapper = mount(Tag, { propsData: { name } })
    expect(wrapper.html()).toContain('</a>')
  })

  it('No remover btn', () => {
    const props = { name: 'Name Tags', readOnly: true }
    const wrapper = mount(Tag, { propsData: props })
    expect(wrapper.html()).not.toContain('</a>')
  })
})
