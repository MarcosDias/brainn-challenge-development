
const getRepositoriesByUser = (callback) => {
  setTimeout(callback, 3000)
}

const getListRepositories = () => {
  return [{
    id: 1,
    name: 'Kubernetes',
    link: 'https://kubernetes.io/',
    description: 'Production-Grade Container Scheduling and Management',
    language: 'go',
    tag: []
  }, {
    id: 2,
    name: 'Django',
    link: 'https://www.djangoproject.com/',
    description: 'The Web framework for perfectionists with deadlines',
    language: 'python',
    tag: []
  }, {
    id: 3,
    name: 'DVWA',
    link: 'http://www.dvwa.co.uk/',
    description: 'Damn Vulnerable Web Application (DVWA)',
    language: 'php',
    tag: []
  }, {
    id: 4,
    name: 'etcd',
    link: 'hthttps://github.com/etcd-io/etcd',
    description: 'Distributed reliable key-value store for the most critical data of distributed system',
    language: 'go',
    tag: []
  }]
}

export default { getRepositoriesByUser, getListRepositories }
